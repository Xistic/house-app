package com.example.houseapi.controller.exceptions;

public class UserAlredyExist extends RuntimeException{
    public UserAlredyExist(String message){
        super(message);
    }
}
