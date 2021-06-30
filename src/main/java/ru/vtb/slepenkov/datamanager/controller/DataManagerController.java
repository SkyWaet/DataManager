package ru.vtb.slepenkov.datamanager.controller;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vtb.slepenkov.datamanager.converter.UserConverter;
import ru.vtb.slepenkov.datamanager.generated.dto.UserWithId;
import ru.vtb.slepenkov.datamanager.generated.dto.UserWithDescription;
import ru.vtb.slepenkov.datamanager.generated.dto.OrderBy;
import ru.vtb.slepenkov.datamanager.service.IUserService;

import javax.validation.Valid;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class DataManagerController {
    private final IUserService service;
    private final UserConverter converter;
    private final OrderBy defaultOrder = new OrderBy();

    @GetMapping("/users")
    public Page<UserWithId> list(@RequestParam(name = "orderBy", required = false) OrderBy orderBy,
                                 @RequestParam(name = "pageIndex", required = false, defaultValue = "0") Integer pageIndex,
                                 @RequestParam(name = "numElements", required = false, defaultValue = "1") Integer numElements) {
        orderBy = Objects.isNull(orderBy) ? defaultOrder : orderBy;
        return service.list(orderBy, pageIndex, numElements).map(converter::toShortDTO);
    }

    @PostMapping("/users")
    public UserWithDescription create(@Valid @RequestBody UserWithDescription user) {
        return converter.toDTO(service.create(converter.from(user)));
    }

    @GetMapping("/user/{userId}")
    public UserWithDescription findById(@PathVariable(name = "userId") Long id) {
        return converter.toDTO(service.findById(id));
    }

    @PutMapping("/user/{userId}")
    public UserWithDescription update(@PathVariable(name = "userId") Long id, @Valid @RequestBody UserWithDescription user) {
        return converter.toDTO(service.update(id, converter.from(user)));
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> delete(@PathVariable(name = "userId") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
