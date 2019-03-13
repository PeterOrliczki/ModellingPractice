package com.codecool.api.exceptions;

import java.io.Serializable;

public class CollapsingFromCarryingWayTooMuchException extends Exception implements Serializable {
    public CollapsingFromCarryingWayTooMuchException() {
    }

    public CollapsingFromCarryingWayTooMuchException(String message) {
        super(message);
    }
}
