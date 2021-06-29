package ru.vtb.slepenkov.datamanager.service;

import ru.vtb.slepenkov.datamanager.exceptions.NoSuchColumnException;
import ru.vtb.slepenkov.datamanager.exceptions.NotFoundException;
import ru.vtb.slepenkov.datamanager.converter.UserConverter;
import ru.vtb.slepenkov.datamanager.model.OrderBy;
import ru.vtb.slepenkov.datamanager.model.SimpleUser;
import ru.vtb.slepenkov.datamanager.model.UserWithDescription;
import ru.vtb.slepenkov.datamanager.model.UserWithId;
import ru.vtb.slepenkov.datamanager.model.User;
import ru.vtb.slepenkov.datamanager.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Getter
@Slf4j
public class UserServiceImpl implements IUserService {

    private final UserRepository repository;
    private final UserConverter converter;

    private final String[] columnNames = {"id", "login", "password", "email", "description"};

    @Override
    public List<UserWithId> list(OrderBy orderBy, Integer pageNumber, Integer numElements) {
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
    public UserWithDescription create(SimpleUser user) {
        return converter.toDTO(repository.save(converter.from(user)));
    }

    @Override
    public UserWithDescription findById(Long id) {
        return converter.toDTO(repository.findById(id).orElseThrow(
                () -> new NotFoundException(404, "User with id " + id + " not found.")));
    }

    @Override
    public UserWithDescription update(Long id, SimpleUser user) {
        User oldUser = repository.findById(id).orElseThrow(
                () -> new NotFoundException(404, "User with id " + id + " not found."));
        oldUser.setLogin(user.getLogin());
        oldUser.setPassword(user.getPassword());
        oldUser.setEmail(user.getEmail());
        oldUser.setDescription(user.getDescription());
        return converter.toDTO(repository.save(oldUser));
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
