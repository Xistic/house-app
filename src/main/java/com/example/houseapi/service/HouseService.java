package com.example.houseapi.service;

import com.example.houseapi.controller.exceptions.UserAlredyExist;
import com.example.houseapi.model.House;
import com.example.houseapi.repo.HouseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HouseService {
    private final HouseRepo houseRepo;

    @Autowired
    public HouseService(HouseRepo houseRepo){
        this.houseRepo = houseRepo;
    }

    public String createHouse(House house) throws Exception {
        Optional<House> existHouse = houseRepo.findByAddress(house.getAddress());
        String houseAddress = house.getAddress();
        if(existHouse.isPresent()) throw new UserAlredyExist("Дом по адресу '" + houseAddress + "' уже зарегистрирован");
        else {
            houseRepo.save(house);
            return String.format("Дом по адресу '%s' успешно зарегистрирован", houseAddress);
        }
    }

    public String deleteHouseByAddress(String address) {
        Optional<House> existHouse = houseRepo.findByAddress(address);

        if (existHouse.isPresent()) {
            houseRepo.delete(existHouse.get());
            return String.format("Дом по адресу '%s' успешно удален из системы", address);
        } else {
            return String.format("Дом по адресу '%s' не найден", address);
        }
    }

    public Optional<House> getHouseByAddress(String address){
        return houseRepo.findByAddress(address);
    }

    public List<House> getAllAddress(){
        return houseRepo.findAll();
    }
}
