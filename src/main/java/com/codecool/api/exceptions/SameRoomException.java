package com.codecool.api.exceptions;

import java.io.Serializable;

public class SameRoomException extends Exception implements Serializable {
    public SameRoomException() {
    }

    public SameRoomException(String message) {
        super(message);
    }
}
