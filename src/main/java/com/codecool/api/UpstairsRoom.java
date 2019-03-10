package com.codecool.api;

public class UpstairsRoom extends Room{

    public UpstairsRoom(String name, RoomLocation roomLocation) {
        super(name, roomLocation);
    }

    @Override
    public void addItem(Item item) {
        getItemsInRoom().add(item);
    }

    @Override
    public void removeItem(Item item) {
        getItemsInRoom().remove(item);
    }
}
