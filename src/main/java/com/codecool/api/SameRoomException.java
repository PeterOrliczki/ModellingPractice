package com.codecool.api;

public class SameRoomException extends Exception {
    public SameRoomException() {
    }

    public SameRoomException(String message) {
        super(message);
    }
}
