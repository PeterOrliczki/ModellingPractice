package com.codecool.api.exceptions;

import java.io.Serializable;

public class DontBelongHereException extends Exception implements Serializable {

    public DontBelongHereException() {
    }

    public DontBelongHereException(String message) {
        super(message);
    }
}
