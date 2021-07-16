package ru.vtb.slepenkov.datamanager.controller;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vtb.slepenkov.datamanager.converter.UserConverter;
import ru.vtb.slepenkov.datamanager.generated.dto.UserDTO;
import ru.vtb.slepenkov.datamanager.generated.dto.UserShortDTO;
import ru.vtb.slepenkov.datamanager.service.IUserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class DataManagerController {
    private final IUserService service;
    private final UserConverter converter;

    @GetMapping("/users")
    public Page<UserShortDTO> list(Pageable pageable) {
        return service.list(pageable).map(converter::toShortDTO);
    }

    @PostMapping("/users")
    public UserDTO create(@Valid @RequestBody UserDTO user) {
        return converter.toDTO(service.create(converter.from(user)));
    }

    @GetMapping("/user/{userId}")
    public UserDTO findById(@PathVariable(name = "userId") Long id) {
        return converter.toDTO(service.findById(id));
    }

    @PutMapping("/user/{userId}")
    public UserDTO update(@PathVariable(name = "userId") Long id, @Valid @RequestBody UserDTO user) {
        return converter.toDTO(service.update(id, converter.from(user)));
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> delete(@PathVariable(name = "userId") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
