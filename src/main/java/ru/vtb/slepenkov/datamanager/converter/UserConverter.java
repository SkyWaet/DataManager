package ru.vtb.slepenkov.datamanager.converter;

import ru.vtb.slepenkov.datamanager.generated.dto.UserDTO;
import ru.vtb.slepenkov.datamanager.generated.dto.UserShortDTO;
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

    public UserShortDTO toShortDTO(User user) {
        return modelMapper.map(user, UserShortDTO.class);
    }

    public UserDTO toDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User from(UserDTO user) {
        return modelMapper.map(user, User.class);
    }
}
