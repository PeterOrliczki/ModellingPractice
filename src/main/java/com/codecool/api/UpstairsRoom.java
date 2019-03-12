package com.codecool.api;

import com.codecool.api.exceptions.CollapsingFromCarryingWayTooMuchException;

public class UpstairsRoom extends Room{

    public UpstairsRoom(String name, RoomLocation roomLocation) {
        super(name, roomLocation);
    }

    @Override
    public void addItem(Item item, PlayerHandItems playerHandItems) throws CollapsingFromCarryingWayTooMuchException {
        if (playerHandItems.getItemsInPlayerHand().size() > 3) {
            throw new CollapsingFromCarryingWayTooMuchException();
        }
        getItemsInRoom().add(item);
    }

    @Override
    public void removeItem(Item item) {
        getItemsInRoom().remove(item);
    }
}
