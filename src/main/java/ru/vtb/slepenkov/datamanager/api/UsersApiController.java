package ru.vtb.slepenkov.datamanager.api;

import ru.vtb.slepenkov.datamanager.model.OrderBy;
import ru.vtb.slepenkov.datamanager.model.SimpleUser;
import ru.vtb.slepenkov.datamanager.model.UserWithDescription;
import ru.vtb.slepenkov.datamanager.model.UserWithId;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.vtb.slepenkov.datamanager.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-22T11:03:20.924Z[GMT]")
@RestController
public class UsersApiController implements UsersApi {

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final UserServiceImpl service;


    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request, UserServiceImpl service) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.service = service;
    }

    public ResponseEntity<List<UserWithId>> usersGet(@Parameter(in = ParameterIn.QUERY, description = "allows to choose the ranking order", schema = @Schema()) @Valid @RequestParam(value = "orderBy", required = false) OrderBy orderBy, @Min(0) @Parameter(in = ParameterIn.QUERY, description = "number of desired page", schema = @Schema(allowableValues = {}
            , defaultValue = "0")) @Valid @RequestParam(value = "pageIndex", required = false, defaultValue = "0") Integer pageIndex, @Min(1) @Max(100) @Parameter(in = ParameterIn.QUERY, description = "", schema = @Schema(allowableValues = {}, minimum = "1", maximum = "100"
            , defaultValue = "20")) @Valid @RequestParam(value = "numElements", required = false, defaultValue = "20") Integer numElements) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                if (orderBy == null) {
                    orderBy = new OrderBy();
                }
                return new ResponseEntity<List<UserWithId>>(service.list(orderBy, pageIndex, numElements), HttpStatus.OK);
            } catch (ApiException e) {
                return new ResponseEntity<List<UserWithId>>(HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<List<UserWithId>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<UserWithDescription> usersPost(@Parameter(in = ParameterIn.DEFAULT, description = "A JSON object containing new user data", required = true, schema = @Schema()) @Valid @RequestBody SimpleUser body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<UserWithDescription>(service.create(body), HttpStatus.OK);
        }

        return new ResponseEntity<UserWithDescription>(HttpStatus.NOT_IMPLEMENTED);
    }

}
