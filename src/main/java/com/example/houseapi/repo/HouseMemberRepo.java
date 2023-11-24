package com.example.houseapi.repo;

import com.example.houseapi.model.House;
import com.example.houseapi.model.HouseMember;
import com.example.houseapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HouseMemberRepo extends JpaRepository<HouseMember, Long> {
    Optional<HouseMember> findByUserAndHouse(User user, House house);

    Optional<HouseMember> findByHouseAndIsOwner(House house, boolean isOwner);


}
