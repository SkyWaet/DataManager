package io.swagger.service;

import io.swagger.api.ApiException;
import io.swagger.api.NotFoundException;
import io.swagger.converter.UserConverter;
import io.swagger.model.OrderBy;
import io.swagger.model.SimpleUser;
import io.swagger.model.UserWithDescription;
import io.swagger.model.UserWithId;
import io.swagger.model.base.User;
import io.swagger.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
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
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserConverter converter;

    private final String[] columnNames = {"id", "login", "password", "email", "description"};

    @Override
    public List<UserWithId> list(OrderBy orderBy, Integer pageNumber, Integer numElements) throws ApiException {
        if (!Arrays.asList(columnNames).contains(orderBy.getColumn())) {
            throw new ApiException(400, "There is no columns with name " + orderBy.getColumn());
        }
        Pageable page;
        if (orderBy.isAscendant()){
            page = PageRequest.of(pageNumber,numElements, Sort.by(orderBy.getColumn()).ascending());
        } else {
            page = PageRequest.of(pageNumber,numElements, Sort.by(orderBy.getColumn()).descending());
        }
        return repository.findAll(page).stream().map(converter::toShortDTO).collect(Collectors.toList());
    }

    @Override
    public UserWithDescription create(SimpleUser user) {
        return converter.toDTO(repository.save(converter.from(user)));
    }

    @Override
    public UserWithDescription findById(Long id) throws ChangeSetPersister.NotFoundException {
        return converter.toDTO(repository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new));
    }

    @Override
    public UserWithDescription update(Long id, SimpleUser user) throws ChangeSetPersister.NotFoundException {
        User oldUser = repository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
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
