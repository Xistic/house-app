package com.example.houseapi.service;

import com.example.houseapi.model.House;
import com.example.houseapi.model.HouseMember;
import com.example.houseapi.model.User;
import com.example.houseapi.repo.HouseMemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HouseMemberService {

    private final HouseService houseService;
    private final UserService userService;
    private final HouseMemberRepo houseMemberRepo;

    @Autowired
    public HouseMemberService(HouseService houseService, UserService userService, HouseMemberRepo houseMemberRepo) {
        this.houseService = houseService;
        this.userService = userService;
        this.houseMemberRepo = houseMemberRepo;
    }

    public String addMemberToHouse(String userName, String houseAddress, boolean isOwner) {
        // Получаем пользователя по имени
        Optional<User> userOptional = userService.getUserByName(userName);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Получаем дом по адресу
            Optional<House> houseOptional = houseService.getHouseByAddress(houseAddress);
            if (houseOptional.isPresent()) {
                House house = houseOptional.get();

                // Проверяем, не является ли пользователь уже членом этого дома
                Optional<HouseMember> existingMember = houseMemberRepo.findByUserAndHouse(user, house);
                if (existingMember.isPresent()) {
                    // Если пользователь уже член этого дома, вы можете обработать это соответственно
                    return ("Пользователь '" + userName + "' уже является членом этого дома.");
                } else {
                    // Проверяем, есть ли уже владелец в этом доме
                    Optional<HouseMember> ownerOptional = houseMemberRepo.findByHouseAndIsOwner(house, true);
                    if (isOwner && ownerOptional.isPresent()) {
                        // Если владелец уже существует и вы пытаетесь добавить еще одного
                        return ("В этом доме уже есть владелец.");
                    }

                    // Создаем нового члена дома
                    HouseMember newMember = new HouseMember();
                    newMember.setUser(user);
                    newMember.setHouse(house);
                    newMember.setOwner(isOwner);

                    // Сохраняем члена дома в репозитории
                    houseMemberRepo.save(newMember);

                    return ("Пользователь '" + userName + "' успешно добавлен в дом '" + houseAddress + "'.");
                }
            } else {
                return ("Дом с адресом '" + houseAddress + "' не найден.");
            }
        } else {
            return ("Пользователь с именем '" + userName + "' не найден.");
        }
    }

    public String removeMemberFromHouse(String houseAddress, String userName) {
        // Получаем пользователя по имени
        Optional<User> userOptional = userService.getUserByName(userName);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Получаем дом по адресу
            Optional<House> houseOptional = houseService.getHouseByAddress(houseAddress);
            if (houseOptional.isPresent()) {
                House house = houseOptional.get();

                // Проверяем, является ли пользователь членом этого дома
                Optional<HouseMember> existingMember = houseMemberRepo.findByUserAndHouse(user, house);
                if (existingMember.isPresent()) {
                    // Удаляем члена дома из репозитория
                    houseMemberRepo.delete(existingMember.get());
                    return "Пользователь '" + userName + "' успешно удален из дома '" + houseAddress + "'.";
                } else {
                    return "Пользователь '" + userName + "' не является членом дома '" + houseAddress + "'.";
                }
            } else {
                return "Дом с адресом '" + houseAddress + "' не найден.";
            }
        } else {
            return "Пользователь с именем '" + userName + "' не найден.";
        }
    }



}
