package com.codecool.api.exceptions;

public class SameRoomException extends Exception {
    public SameRoomException() {
    }

    public SameRoomException(String message) {
        super(message);
    }
}
