package com.example.houseapi.service;

import com.example.houseapi.controller.exceptions.UserAlredyExist;
import com.example.houseapi.model.User;
import com.example.houseapi.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    public String createUser(User user) throws Exception {
        Optional<User> existUser = userRepo.findByName(user.getName());
        String userLogin = user.getName();
        if(existUser.isPresent()) throw new UserAlredyExist("Логин '" + userLogin + "' уже занят");
        else {
            userRepo.save(user);
            return String.format("Пользователь с логином '%s' успешно зарегистрирован", userLogin);
        }
    }

    public String deleteUser(String userLogin) {
        Optional<User> existUser = userRepo.findByName(userLogin);

        if (existUser.isPresent()) {
            userRepo.delete(existUser.get());
            return String.format("Пользователь с логином '%s' успешно удален", userLogin);
        } else {
            return String.format("Пользователь с логином '%s' не найден", userLogin);
        }
    }

    public Optional<User> getUserByName(String name){
        return userRepo.findByName(name);
    }

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }
}
