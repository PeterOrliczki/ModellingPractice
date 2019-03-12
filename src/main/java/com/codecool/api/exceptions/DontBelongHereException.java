package com.codecool.api.exceptions;

public class DontBelongHereException extends Exception {

    public DontBelongHereException() {
    }

    public DontBelongHereException(String message) {
        super(message);
    }
}
