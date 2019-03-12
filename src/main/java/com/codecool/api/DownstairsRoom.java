package com.codecool.api;

import com.codecool.api.exceptions.DontBelongHereException;

public class DownstairsRoom extends Room {

    public DownstairsRoom(String name, RoomLocation roomLocation) {
        super(name, roomLocation);
    }

    @Override
    public void addItem(Item item) throws DontBelongHereException {
        if (item.getTypeOfItem() == RoomType.KITCHEN) {
            throw new DontBelongHereException();
        }
        getItemsInRoom().add(item);
    }

    @Override
    public void removeItem(Item item) {
        getItemsInRoom().remove(item);
    }

}
