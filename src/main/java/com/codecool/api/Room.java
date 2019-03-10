package com.codecool.api;

import java.util.ArrayList;

public abstract class Room{
    private String name;
    private int maxItems;
    private ArrayList<Item> itemsInRoom = new ArrayList<>();

    public abstract void addItem(Item item);

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

    @Override
    public String toString() {
        return "Room{" +
            "name='" + name + '\'' +
            ", maxItems=" + maxItems +
            ", itemsInPlayerHand=" + itemsInRoom +
            '}';
    }
}
