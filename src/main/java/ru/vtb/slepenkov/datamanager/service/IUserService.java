package ru.vtb.slepenkov.datamanager.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.vtb.slepenkov.datamanager.generated.dto.OrderBy;
import ru.vtb.slepenkov.datamanager.model.User;


public interface IUserService {
    Page<User> list(Pageable pageable);

    User create(User user);

    User findById(Long id);

    User update(Long id, User user);

    void delete(Long id);
}
