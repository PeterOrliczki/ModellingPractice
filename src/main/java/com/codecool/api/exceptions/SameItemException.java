package com.codecool.api.exceptions;

import java.io.Serializable;

public class SameItemException extends Exception implements Serializable {
    public SameItemException() {
    }

    public SameItemException(String message) {
        super(message);
    }
}
