package com.example.houseapi.repo;

import com.example.houseapi.model.House;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HouseRepo extends JpaRepository<House, Long> {
    Optional<House> findByAddress(String address);
}
