package com.codecool.api;

import com.codecool.api.exceptions.CollapsingFromCarryingWayTooMuchException;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Room implements Serializable {
    private String name;
    private RoomLocation roomLocation;
    private int maxItems;
    private ArrayList<Item> itemsInRoom;

    public Room(String name, RoomLocation roomLocation) {
        this.name = name;
        this.roomLocation = roomLocation;
        itemsInRoom = new ArrayList<>();
    }

    public abstract void addItem(Item item, PlayerHandItems playerHandItems) throws CollapsingFromCarryingWayTooMuchException;

    public abstract void removeItem(Item item);

    String getName() {
        return name;
    }

    public int getMaxItems() {
        return maxItems;
    }

    ArrayList<Item> getItemsInRoom() {
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
