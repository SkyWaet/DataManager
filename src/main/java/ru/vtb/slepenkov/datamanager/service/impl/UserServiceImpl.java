package ru.vtb.slepenkov.datamanager.service.impl;

import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import ru.vtb.slepenkov.datamanager.exceptions.UserNotFoundException;
import ru.vtb.slepenkov.datamanager.model.QUser;
import ru.vtb.slepenkov.datamanager.model.User;
import ru.vtb.slepenkov.datamanager.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.vtb.slepenkov.datamanager.service.AbstractBaseService;
import ru.vtb.slepenkov.datamanager.service.IUserService;

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
                () -> new UserNotFoundException(id));
    }

    @Override
    public User update(Long id, User user) {
        User oldUser = findById(id);
        user.setId(id);
        user.setCreatedAt(oldUser.getCreatedAt());
        return save(user);
    }

    @Override
    public void delete(Long id) {
        User deletedUser = findById(id);
        deletedUser.setDeleted(true);
        save(deletedUser);
    }
}
