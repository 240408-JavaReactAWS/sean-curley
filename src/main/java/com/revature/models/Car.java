package com.revature.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity //This annotation makes a database table
@Table(name = "cars") //This allows us to name a table
@Component //This is a Spring Bean
public class Car {

    @Id //carId is the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //It should auto-increment
    private int carId;
    private String engineType;
    private String color;

    //No-args constructor
    //Needed for Jackson databind
    public Car() {
    }

    //All-args constructor
    public Car(String color, String engineType, int carId) {
        this.color = color;
        this.engineType = engineType;
        this.carId = carId;
    }


    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", engineType='" + engineType + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
