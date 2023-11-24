package com.example.houseapi.repo;

import com.example.houseapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByName (String login);
}
