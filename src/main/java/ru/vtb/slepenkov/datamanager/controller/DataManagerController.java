package ru.vtb.slepenkov.datamanager.controller;


import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vtb.slepenkov.datamanager.generated.dto.UserWithId;
import ru.vtb.slepenkov.datamanager.generated.dto.UserWithDescription;
import ru.vtb.slepenkov.datamanager.generated.dto.OrderBy;
import ru.vtb.slepenkov.datamanager.service.IUserService;

import javax.validation.Valid;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class DataManagerController {
    private final IUserService service;
    private final OrderBy defaultOrder = new OrderBy();

    @GetMapping("/users")
    public List<UserWithId> list(@RequestParam(name = "orderBy", required = false) OrderBy orderBy,
                                 @RequestParam(name = "pageIndex", required = false, defaultValue ="0") Integer pageIndex,
                                 @RequestParam(name = "numElements", required = false, defaultValue ="1" ) Integer numElements) {
        orderBy = Objects.isNull(orderBy) ? defaultOrder : orderBy;
        return service.list(orderBy, pageIndex, numElements);
    }

    @PostMapping("/users")
    public UserWithDescription create(@Valid @RequestBody UserWithDescription user) {
        return service.create(user);
    }

    @GetMapping("/user/{userId}")
    public UserWithDescription findById(@PathVariable(name = "userId") Long id) {
        return service.findById(id);
    }

    @PutMapping("/user/{userId}")
    public UserWithDescription update(@PathVariable(name = "userId") Long id, @Valid @RequestBody UserWithDescription user) {
        return service.update(id, user);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> delete(@PathVariable(name = "userId") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
