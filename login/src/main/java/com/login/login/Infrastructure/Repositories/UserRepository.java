package com.login.login.Infrastructure.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.login.login.Domain.User;


public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username); 
}