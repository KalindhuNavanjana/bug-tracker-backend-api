package io.entgra.bugtrackerapi.mapper;

import io.entgra.bugtrackerapi.dto.UserDto;
import io.entgra.bugtrackerapi.entity.User;

public class UserMapper {
    public static UserDto mapToUserDto(User user){
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName()
        );
    }

    public static User mapToUser(UserDto userDto){
        return new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName()
        );
    }
}
