package io.swagger.converter;

import io.swagger.model.SimpleUser;
import io.swagger.model.UserWithDescription;
import io.swagger.model.UserWithId;
import io.swagger.model.base.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserConverter {

    @Qualifier("standardModelMapper")
    private final ModelMapper modelMapper;

    public UserWithId toShortDTO(User user) {
        return modelMapper.map(user, UserWithId.class);
    }

    public UserWithDescription toDTO( User user) {
        return modelMapper.map(user, UserWithDescription.class);
    }

    public User from(SimpleUser user) {
        return modelMapper.map(user, User.class);
    }
}
