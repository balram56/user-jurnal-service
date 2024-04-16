package com.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.userservice.entity.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<Object> findByUsername(String username);
}
