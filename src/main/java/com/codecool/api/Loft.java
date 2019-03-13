package com.codecool.api;

import com.codecool.api.exceptions.CollapsingFromCarryingWayTooMuchException;

import java.io.Serializable;

public class Loft extends Room implements Serializable {

    public Loft(String name, RoomLocation roomLocation) {
        super(name, roomLocation);
    }

    @Override
    public void addItem(Item item, PlayerHandItems playerHandItems) throws CollapsingFromCarryingWayTooMuchException {
        if (playerHandItems.getItemsInPlayerHand().size() > 2) {
            throw new CollapsingFromCarryingWayTooMuchException();
        }
        getItemsInRoom().add(item);
    }

    @Override
    public void removeItem(Item item) {
        getItemsInRoom().remove(item);
    }
}
