package com.revature.exceptions;

//Thrown whenever a request tries to access an item not found in the database
public class CarNotFoundException extends Exception{

    public CarNotFoundException(String e)
    {
        super(e);
    }
}
