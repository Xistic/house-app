package com.example.houseapi.controller;

import com.example.houseapi.model.House;
import com.example.houseapi.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/houses")
public class HouseController {
    private final HouseService houseService;

    @Autowired
    public HouseController(HouseService houseService){
        this.houseService = houseService;
    }

    @PostMapping("/create")
    public String createHouse(@RequestBody House house) throws Exception{
        return houseService.createHouse(house);
    }

    @DeleteMapping("/deleteHouse/{houseAddress}")
    public ResponseEntity<String> deleteHouse(@PathVariable String houseAddress){
        try {
            String message = houseService.deleteHouseByAddress(houseAddress);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllAddress")
    public List<House> getAllHouses(){
        return houseService.getAllAddress();
    }

}
