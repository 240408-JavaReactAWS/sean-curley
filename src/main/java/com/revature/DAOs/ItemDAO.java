package com.revature.DAOs;

import com.revature.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
//JpaRepository already has the necessary CRUD operations for a DAO, so we just extend that
public interface ItemDAO extends JpaRepository<Item, Integer> {
}
