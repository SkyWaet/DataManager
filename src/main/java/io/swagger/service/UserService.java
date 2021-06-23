package io.swagger.service;


import io.swagger.api.ApiException;
import io.swagger.api.NotFoundException;
import io.swagger.model.OrderBy;
import io.swagger.model.SimpleUser;
import io.swagger.model.UserWithDescription;
import io.swagger.model.UserWithId;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;

import java.util.List;


public interface UserService {
    List<UserWithId> list(OrderBy orderBy, Integer pageNumber, Integer numElements) throws ApiException;

    UserWithDescription create(SimpleUser user);

    UserWithDescription findById(Long id) throws ChangeSetPersister.NotFoundException;

    UserWithDescription update(Long id, SimpleUser user) throws ChangeSetPersister.NotFoundException;

    void delete(Long id) throws NotFoundException;
}
