package io.entgra.bugtrackerapi.service.impl;

import io.entgra.bugtrackerapi.dto.UserDto;
import io.entgra.bugtrackerapi.entity.User;
import io.entgra.bugtrackerapi.exception.ResourceNotFound;
import io.entgra.bugtrackerapi.mapper.UserMapper;
import io.entgra.bugtrackerapi.repository.UserRepository;
import io.entgra.bugtrackerapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users =  userRepository.findAll();
        return users.stream().map(
                (user) -> UserMapper.mapToUserDto(user)
        ).collect(Collectors.toList());
    }
    @Override
    public UserDto getUser(Long userID) {
        User user = userRepository.findById(userID).orElseThrow(
                ()-> new ResourceNotFound("User Not Found")
        );
        return UserMapper.mapToUserDto(user);
    }
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);
        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public UserDto updateUser(Long userID, UserDto updatedUser) {
        User user = userRepository.findById(userID).orElseThrow(
                ()-> new ResourceNotFound("User Not Found")
        );

        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());

        User updatedUserObj = userRepository.save(user);
        return UserMapper.mapToUserDto(updatedUserObj);
    }

    @Override
    public UserDto deleteUser(Long userID) {
        User user = userRepository.findById(userID).orElseThrow(
                ()-> new ResourceNotFound("User Not Found")
        );
        userRepository.deleteById(userID);
        return UserMapper.mapToUserDto(user);
    }
}
