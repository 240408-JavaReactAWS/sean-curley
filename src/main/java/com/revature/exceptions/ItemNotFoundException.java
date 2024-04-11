package com.revature.exceptions;

//Thrown whenever a request tries to access an item not found in the database
public class ItemNotFoundException extends Exception{

    public ItemNotFoundException(String e)
    {
        super(e);
    }
}
