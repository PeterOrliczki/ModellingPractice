package com.codecool.api;

public class SameItemException extends Exception {
    public SameItemException() {
    }

    public SameItemException(String message) {
        super(message);
    }
}
