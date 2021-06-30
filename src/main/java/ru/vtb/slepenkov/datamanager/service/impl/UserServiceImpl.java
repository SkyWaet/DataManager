package ru.vtb.slepenkov.datamanager.service.impl;

import com.querydsl.core.BooleanBuilder;
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

@Service
@AllArgsConstructor
@Getter
@Slf4j
public class UserServiceImpl extends AbstractBaseService<User, Long, QUser, UserRepository>
        implements IUserService {

    private final UserRepository repository;

    @Override
    public Page<User> list(Pageable pageable) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(QUser.user.deleted.isFalse());
        return findAll(booleanBuilder, pageable);
    }

    @Override
    public User create(User user) {
        return save(user);
    }

    @Override
    public User findById(Long id) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(QUser.user.deleted.isFalse());
        booleanBuilder.and(QUser.user.id.eq(id));
        return get(booleanBuilder).orElseThrow(
                () -> new NotFoundException(404, "User with id " + id + " not found."));
    }

    @Override
    public User update(Long id, User user) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(QUser.user.deleted.isFalse());
        booleanBuilder.and(QUser.user.id.eq(id));

        User oldUser = get(booleanBuilder).orElseThrow(
                () -> new NotFoundException(404, "User with id " + id + " not found."));
        user.setId(id);
        user.setCreatedAt(oldUser.getCreatedAt());
        return save(user);
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(QUser.user.deleted.isFalse());
        booleanBuilder.and(QUser.user.id.eq(id));

        User deletedUser = get(booleanBuilder).orElseThrow(
                () -> new NotFoundException(404, "User with id " + id + " not found."));
        deletedUser.setDeleted(true);
        save(deletedUser);
    }
}
