package com.example.houseapi.controller;

import com.example.houseapi.service.HouseMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/houseMembers")
public class HouseMemberController {
    private final HouseMemberService houseMemberService;

    @Autowired
    public HouseMemberController(HouseMemberService houseMemberService){
        this.houseMemberService = houseMemberService;
    }

    @PostMapping("/add-member")
    public ResponseEntity<String> addMemberToHouse(@RequestBody Map<String, Object> request) {
        String userName = (String) request.get("userName");
        String houseAddress = (String) request.get("houseAddress");
        boolean isOwner = (boolean) request.get("isOwner");

        String result = houseMemberService.addMemberToHouse(userName, houseAddress, isOwner);
        return ResponseEntity.ok(result);
    }
}
