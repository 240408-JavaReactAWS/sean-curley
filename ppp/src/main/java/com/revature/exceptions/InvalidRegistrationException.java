package com.revature.exceptions;

public class InvalidRegistrationException extends RuntimeException {

    public InvalidRegistrationException(String e)
    {
        super(e);
    }
}
