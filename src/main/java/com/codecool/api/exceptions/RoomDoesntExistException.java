package com.codecool.api.exceptions;

import java.io.Serializable;

public class RoomDoesntExistException extends Exception implements Serializable {

    public RoomDoesntExistException() {
    }

    public RoomDoesntExistException(String message) {
        super(message);
    }
}
