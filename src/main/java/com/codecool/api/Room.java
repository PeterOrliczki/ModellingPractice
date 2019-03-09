package com.codecool.api;

import java.util.ArrayList;

public abstract class Room{
    private String id;
    private int maxItems;
    private ArrayList<Item> itemsInRoom = new ArrayList<>();

    abstract void addItem(Item item);

    abstract void removeItem(Item item);

    public String getId() {
        return id;
    }

    public int getMaxItems() {
        return maxItems;
    }

    public ArrayList<Item> getItemsInRoom() {
        return itemsInRoom;
    }

    @Override
    public String toString() {
        return "Room{" +
            "id='" + id + '\'' +
            ", maxItems=" + maxItems +
            ", itemsInPlayerHand=" + itemsInRoom +
            '}';
    }
}
