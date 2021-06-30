package ru.vtb.slepenkov.datamanager.service.impl;

import org.springframework.data.domain.Page;
import ru.vtb.slepenkov.datamanager.exceptions.NoSuchColumnException;
import ru.vtb.slepenkov.datamanager.exceptions.NotFoundException;
import ru.vtb.slepenkov.datamanager.converter.UserConverter;
import ru.vtb.slepenkov.datamanager.generated.dto.UserWithId;
import ru.vtb.slepenkov.datamanager.generated.dto.UserWithDescription;
import ru.vtb.slepenkov.datamanager.generated.dto.OrderBy;
import ru.vtb.slepenkov.datamanager.model.QUser;
import ru.vtb.slepenkov.datamanager.model.User;
import ru.vtb.slepenkov.datamanager.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.vtb.slepenkov.datamanager.service.AbstractBaseService;
import ru.vtb.slepenkov.datamanager.service.IUserService;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Getter
@Slf4j
public class UserServiceImpl extends AbstractBaseService<User, Long, QUser, UserRepository>
        implements IUserService {

    private final UserRepository repository;
    private final UserConverter converter;

    private final String[] columnNames = {"id", "login", "password", "email", "description"};

    @Override
    public Page<UserWithId> list(OrderBy orderBy, Integer pageNumber, Integer numElements) {
        if (!Arrays.asList(columnNames).contains(orderBy.getColumn())) {
            throw new NoSuchColumnException(400, "There is no columns with name " + orderBy.getColumn());
        }
        Pageable page;
        if (orderBy.isAscendant()) {
            page = PageRequest.of(pageNumber, numElements, Sort.by(orderBy.getColumn()).ascending());
        } else {
            page = PageRequest.of(pageNumber, numElements, Sort.by(orderBy.getColumn()).descending());
        }
        return repository.findAll(page).stream().map(converter::toShortDTO).collect(Collectors.toList());
    }

    @Override
    public UserWithDescription create(UserWithDescription user) {
        return converter.toDTO(repository.save(converter.from(user)));
    }

    @Override
    public UserWithDescription findById(Long id) {
        return converter.toDTO(repository.findById(id).orElseThrow(
                () -> new NotFoundException(404, "User with id " + id + " not found.")));
    }

    @Override
    public UserWithDescription update(Long id, UserWithDescription user) {
        User oldUser = repository.findById(id).orElseThrow(
                () -> new NotFoundException(404, "User with id " + id + " not found."));
        user.setId(id);
        User updatedUser = converter.from(user);
        updatedUser.setCreatedAt(oldUser.getCreatedAt());
        return converter.toDTO(repository.save(updatedUser));
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new NotFoundException(404, "User with id " + id + " does not exist");
        }

    }
}
