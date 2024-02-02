package io.entgra.bugtrackerapi.service;

import io.entgra.bugtrackerapi.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
    UserDto getUser(Long userID);
    UserDto createUser(UserDto userDto);
    UserDto updateUser(Long userID, UserDto updatedUser);
    UserDto deleteUser(Long userID);
}
