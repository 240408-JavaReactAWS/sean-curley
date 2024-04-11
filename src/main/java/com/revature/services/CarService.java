package com.revature.services;

import com.revature.DAOs.CarDAO;
import com.revature.exceptions.CarNotFoundException;
import org.springframework.stereotype.Service;
import com.revature.models.Car;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private CarDAO carDAO;

    public CarService(CarDAO carDAO)
    {
        this.carDAO = carDAO;
    }

    public Car addCar(Car car)
    {
        return carDAO.save(car);
    }
    public List<Car> getAllCars()
    {
        return carDAO.findAll();
    }

    public Car getCarById(int id) throws CarNotFoundException
    {
        return carDAO.findById(id).orElseThrow(() -> new CarNotFoundException("Car not found!"));
    }
}
