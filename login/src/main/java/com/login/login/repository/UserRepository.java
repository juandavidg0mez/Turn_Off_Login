package com.login.login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.login.login.User.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserName(String username);
}
