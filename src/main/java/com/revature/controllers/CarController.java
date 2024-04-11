package com.revature.controllers;

import com.revature.DAOs.CarDAO;
import com.revature.exceptions.CarNotFoundException;
import com.revature.models.Car;
import com.revature.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*
Used to handle traffic to the "/cars" endpoint.
 */

@RestController //Bean to convert HTTP requests
@RequestMapping("/cars") //Any HTTP request ending in /cars will go to this app
@ResponseBody //Turns response data into JSON automatically
public class CarController {

    @Autowired
    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping()
    public ResponseEntity<Car> addCarHandler(@RequestBody Car car)
    {
        return ResponseEntity.ok(carService.addCar(car));
    }

    @GetMapping()
    public ResponseEntity<List<Car>> getAllCarsHandler()
    {
        return ResponseEntity.ok(carService.getAllCars());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarByIdHandler(@PathVariable int id)
    {
        try {
            return ResponseEntity.ok(carService.getCarById(id));
        }
        catch(CarNotFoundException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("{id}/{color}")
    public ResponseEntity<Car> changeColorByIdHandler(@PathVariable int id, @PathVariable String color)
    {
        try {
            Car toChange = carService.getCarById(id);
            toChange.setColor(color);
            return ResponseEntity.ok(carService.addCar(toChange));
        }
        catch(CarNotFoundException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
