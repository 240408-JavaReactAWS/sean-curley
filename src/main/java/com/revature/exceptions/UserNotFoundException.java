package com.revature.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String e)
    {
        super(e);
    }
}
