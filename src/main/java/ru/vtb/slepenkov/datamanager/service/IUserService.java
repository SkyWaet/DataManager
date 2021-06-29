package ru.vtb.slepenkov.datamanager.service;


import ru.vtb.slepenkov.datamanager.exceptions.ApiException;
import ru.vtb.slepenkov.datamanager.exceptions.NotFoundException;
import ru.vtb.slepenkov.datamanager.model.OrderBy;
import ru.vtb.slepenkov.datamanager.model.SimpleUser;
import ru.vtb.slepenkov.datamanager.model.UserWithDescription;
import ru.vtb.slepenkov.datamanager.model.UserWithId;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;


public interface IUserService {
    List<UserWithId> list(OrderBy orderBy, Integer pageNumber, Integer numElements) throws ApiException;

    UserWithDescription create(SimpleUser user);

    UserWithDescription findById(Long id) throws ChangeSetPersister.NotFoundException;

    UserWithDescription update(Long id, SimpleUser user) throws ChangeSetPersister.NotFoundException;

    void delete(Long id) throws NotFoundException;
}
