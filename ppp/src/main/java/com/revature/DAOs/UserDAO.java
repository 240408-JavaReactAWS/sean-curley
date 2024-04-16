package com.revature.DAOs;

import com.revature.models.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//JpaRepository already has the necessary CRUD operations for a DAO, so we just extend that
@Repository
public interface UserDAO extends JpaRepository<User, Integer>{

    public Optional<User> findByUsername(String username);
    public Optional<User> findByUsernameAndToken(String username, String token);
    public Optional<User> findByUsernameAndPassword(String username, String password);
    public Optional<User> findByToken(String token);
    }