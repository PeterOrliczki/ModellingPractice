package com.codecool.api;

import com.codecool.api.exceptions.DontBelongHereException;

import java.util.ArrayList;

public abstract class Room{
    private String name;
    private RoomLocation roomLocation;
    private int maxItems;
    private ArrayList<Item> itemsInRoom;

    public Room(String name, RoomLocation roomLocation) {
        this.name = name;
        this.roomLocation = roomLocation;
        itemsInRoom = new ArrayList<>();
    }

    public abstract void addItem(Item item) throws DontBelongHereException;

    public abstract void removeItem(Item item);

    public String getName() {
        return name;
    }

    public int getMaxItems() {
        return maxItems;
    }

    public ArrayList<Item> getItemsInRoom() {
        return itemsInRoom;
    }

    public RoomLocation getRoomLocation() {
        return roomLocation;
    }

    @Override
    public String toString() {
        return "Room{" +
            "name='" + name + '\'' +
            ", roomLocation=" + roomLocation +
            ", maxItems=" + maxItems +
            ", itemsInRoom=" + itemsInRoom +
            '}';
    }
}
