package io.swagger.api;

import io.swagger.model.SimpleUser;
import io.swagger.model.UserWithDescription;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-22T11:03:20.924Z[GMT]")
@RestController
public class UserApiController implements UserApi {

    private static final Logger log = LoggerFactory.getLogger(UserApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public UserApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> userUserIdDelete(@Min(0L)@Parameter(in = ParameterIn.PATH, description = "unique id of desired user", required=true, schema=@Schema(allowableValues={  }
)) @PathVariable("userId") Long userId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<UserWithDescription> userUserIdGet(@Min(0L)@Parameter(in = ParameterIn.PATH, description = "unique id of desired user", required=true, schema=@Schema(allowableValues={  }
)) @PathVariable("userId") Long userId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<UserWithDescription>(objectMapper.readValue("{\n  \"password\" : \"\",\n  \"id\" : 0,\n  \"login\" : \"login\",\n  \"email\" : \"\",\n  \"desc\" : \"desc\"\n}", UserWithDescription.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<UserWithDescription>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<UserWithDescription>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<UserWithDescription> userUserIdPut(@Min(0L)@Parameter(in = ParameterIn.PATH, description = "unique id of desired user", required=true, schema=@Schema(allowableValues={  }
)) @PathVariable("userId") Long userId,@Parameter(in = ParameterIn.DEFAULT, description = "A JSON object containing new user data", required=true, schema=@Schema()) @Valid @RequestBody SimpleUser body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<UserWithDescription>(objectMapper.readValue("{\n  \"password\" : \"\",\n  \"id\" : 0,\n  \"login\" : \"login\",\n  \"email\" : \"\",\n  \"desc\" : \"desc\"\n}", UserWithDescription.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<UserWithDescription>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<UserWithDescription>(HttpStatus.NOT_IMPLEMENTED);
    }

}
