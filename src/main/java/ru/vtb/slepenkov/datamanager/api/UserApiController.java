package ru.vtb.slepenkov.datamanager.api;

import ru.vtb.slepenkov.datamanager.exceptions.NotFoundException;
import ru.vtb.slepenkov.datamanager.model.SimpleUser;
import ru.vtb.slepenkov.datamanager.model.UserWithDescription;
import ru.vtb.slepenkov.datamanager.service.UserServiceImpl;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;


@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-22T11:03:20.924Z[GMT]")
@RestController
public class UserApiController implements UserApi {

    private static final Logger log = LoggerFactory.getLogger(UserApiController.class);

    private final HttpServletRequest request;

    private final UserServiceImpl service;

    @org.springframework.beans.factory.annotation.Autowired
    public UserApiController(HttpServletRequest request, UserServiceImpl service) {
        this.request = request;
        this.service = service;
    }

    public ResponseEntity<Void> userUserIdDelete(@Min(0L) @Parameter(in = ParameterIn.PATH, description = "unique id of desired user", required = true, schema = @Schema(allowableValues = {}
    )) @PathVariable("userId") Long userId) {
        String accept = request.getHeader("Accept");
        service.delete(userId);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<UserWithDescription> userUserIdGet(@Min(0L) @Parameter(in = ParameterIn.PATH, description = "unique id of desired user", required = true, schema = @Schema(allowableValues = {}
    )) @PathVariable("userId") Long userId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<UserWithDescription>(service.findById(userId), HttpStatus.OK);
        }

        return new ResponseEntity<UserWithDescription>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<UserWithDescription> userUserIdPut(@Min(0L) @Parameter(in = ParameterIn.PATH, description = "unique id of desired user", required = true, schema = @Schema(allowableValues = {}
    )) @PathVariable("userId") Long userId, @Parameter(in = ParameterIn.DEFAULT, description = "A JSON object containing new user data", required = true, schema = @Schema()) @Valid @RequestBody SimpleUser body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<UserWithDescription>(service.update(userId, body), HttpStatus.OK);
        }

        return new ResponseEntity<UserWithDescription>(HttpStatus.NOT_IMPLEMENTED);
    }

}
