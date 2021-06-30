package ru.vtb.slepenkov.datamanager.service;


import org.springframework.data.domain.Page;
import ru.vtb.slepenkov.datamanager.generated.dto.OrderBy;
import ru.vtb.slepenkov.datamanager.model.User;


public interface IUserService {
    Page<User> list(OrderBy orderBy, Integer pageNumber, Integer numElements);

    User create(User user);

    User findById(Long id);

    User update(Long id, User user);

    void delete(Long id);
}
