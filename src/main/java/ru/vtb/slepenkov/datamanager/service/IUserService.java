package ru.vtb.slepenkov.datamanager.service;


import org.springframework.data.domain.Page;
import ru.vtb.slepenkov.datamanager.exceptions.ApiException;
import ru.vtb.slepenkov.datamanager.exceptions.NotFoundException;
import ru.vtb.slepenkov.datamanager.generated.dto.OrderBy;
import ru.vtb.slepenkov.datamanager.generated.dto.UserWithDescription;
import ru.vtb.slepenkov.datamanager.generated.dto.UserWithId;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;


public interface IUserService {
    Page<UserWithId> list(OrderBy orderBy, Integer pageNumber, Integer numElements);

    UserWithDescription create(UserWithDescription user);

    UserWithDescription findById(Long id);

    UserWithDescription update(Long id, UserWithDescription user);

    void delete(Long id);
}
