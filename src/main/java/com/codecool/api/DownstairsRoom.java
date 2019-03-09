package com.codecool.api;

public class DownstairsRoom extends Room {

    public DownstairsRoom() {
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
