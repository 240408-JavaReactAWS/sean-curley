package com.revature.DAOs;

import com.revature.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository has the necessary CRUD functions for a DAO, so we just extend it for our own
//Car is the Object, Integer is the primary key
public interface CarDAO extends JpaRepository<Car, Integer> {
}
