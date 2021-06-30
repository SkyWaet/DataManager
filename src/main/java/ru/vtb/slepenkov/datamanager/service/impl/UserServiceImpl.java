package ru.vtb.slepenkov.datamanager.service.impl;

import org.springframework.data.domain.Page;
import ru.vtb.slepenkov.datamanager.exceptions.NoSuchColumnException;
import ru.vtb.slepenkov.datamanager.exceptions.NotFoundException;
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

    private final String[] columnNames = {"id", "login", "password", "email", "description"};

    @Override
    public Page<User> list(OrderBy orderBy, Integer pageNumber, Integer numElements) {
        if (!Arrays.asList(columnNames).contains(orderBy.getColumn())) {
            throw new NoSuchColumnException(400, "There is no columns with name " + orderBy.getColumn());
        }
        Pageable page;
        if (orderBy.isAscendant()) {
            page = PageRequest.of(pageNumber, numElements, Sort.by(orderBy.getColumn()).ascending());
        } else {
            page = PageRequest.of(pageNumber, numElements, Sort.by(orderBy.getColumn()).descending());
        }
        return null;
    }

    @Override
    public User create(User user) {
        return save(user);
    }

    @Override
    public User findById(Long id) {
        return get(id).orElseThrow(
                () -> new NotFoundException(404, "User with id " + id + " not found."));
    }

    @Override
    public User update(Long id, User user) {
        User oldUser = get(id).orElseThrow(
                () -> new NotFoundException(404, "User with id " + id + " not found."));
        user.setId(id);
        user.setCreatedAt(oldUser.getCreatedAt());
        return save(user);
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        User deletedUser = get(id).orElseThrow(
                () -> new NotFoundException(404, "User with id " + id + " not found."));
        deletedUser.setDeleted(true);
        save(deletedUser);
    }
}
