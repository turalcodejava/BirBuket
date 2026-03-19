package com.birbuket.exception;

public class UnderageUserException extends RuntimeException {
    public UnderageUserException(String message) {
        super(message);
    }
}
