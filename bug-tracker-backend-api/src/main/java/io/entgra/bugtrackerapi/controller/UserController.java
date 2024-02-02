package io.entgra.bugtrackerapi.controller;

import io.entgra.bugtrackerapi.dto.UserDto;
import io.entgra.bugtrackerapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @GetMapping()
    public ResponseEntity<List<UserDto>>getAllUsers(){
        List<UserDto> users= userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto>getUserByID(@PathVariable("id") Long userID){
        UserDto user = userService.getUser(userID);
        return ResponseEntity.ok(user);
    }
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto savedUser = userService.createUser(userDto);
        return new ResponseEntity<>(savedUser,HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    public ResponseEntity<UserDto>updateUser(@PathVariable("id") Long userID, @RequestBody UserDto updatedUser){
        UserDto user = userService.updateUser(userID,updatedUser);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<UserDto>deleteUserByID(@PathVariable("id") Long userID){
        UserDto user = userService.deleteUser(userID);
        return ResponseEntity.ok(user);
    }
}
