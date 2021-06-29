package ru.vtb.slepenkov.datamanager.converter;

import ru.vtb.slepenkov.datamanager.model.SimpleUser;
import ru.vtb.slepenkov.datamanager.generated.dto.UserWithId;
import ru.vtb.slepenkov.datamanager.generated.dto.UserWithDescription;
import ru.vtb.slepenkov.datamanager.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserConverter {

    @Qualifier("standardModelMapper")
    private final ModelMapper modelMapper;

    public UserWithId toShortDTO(User user) {
        return modelMapper.map(user, UserWithId.class);
    }

    public UserWithDescription toDTO(User user) {
        return modelMapper.map(user, UserWithDescription.class);
    }

    public User from(UserWithDescription user) {
        return modelMapper.map(user, User.class);
    }
}
