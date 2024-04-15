package com.revature.DAOs;

import com.revature.models.Item;
import com.revature.models.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//JpaRepository already has the necessary CRUD operations for a DAO, so we just extend that
@Repository
public interface ItemDAO extends JpaRepository<Item, Integer> {
    public Optional<List<Item>> findByUserId(int userId);
}
