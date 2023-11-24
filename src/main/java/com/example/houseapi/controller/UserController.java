package com.example.houseapi.controller;

import com.example.houseapi.model.User;
import com.example.houseapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/create")
    public String createUser(@RequestBody User user) throws Exception {
        return userService.createUser(user);
    }

    @DeleteMapping("/deleteUser/{userLogin}")
    public ResponseEntity<String> deleteUser(@PathVariable String userLogin){
        try {
            String message = userService.deleteUser(userLogin);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

}
