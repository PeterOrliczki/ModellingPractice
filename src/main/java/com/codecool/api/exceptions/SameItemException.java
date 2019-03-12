package com.codecool.api.exceptions;

public class SameItemException extends Exception {
    public SameItemException() {
    }

    public SameItemException(String message) {
        super(message);
    }
}
