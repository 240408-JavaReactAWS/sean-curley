package com.revature.exceptions;

public class InvalidAuthenticationException extends RuntimeException {
    public InvalidAuthenticationException(String e) {
        super(e);
    }
}