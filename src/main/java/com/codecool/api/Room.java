package com.codecool.api;

import java.util.ArrayList;

public abstract class Room{
    private String id;
    private int maxItems;
    private ArrayList<PlayerHandItems> itemsInPlayerHand = new ArrayList<>();

    abstract void addItem();

    abstract void removeItem();

    public String getId() {
        return id;
    }

    public int getMaxItems() {
        return maxItems;
    }

    public ArrayList<PlayerHandItems> getItemsInPlayerHand() {
        return itemsInPlayerHand;
    }

    @Override
    public String toString() {
        return "Room{" +
            "id='" + id + '\'' +
            ", maxItems=" + maxItems +
            ", itemsInPlayerHand=" + itemsInPlayerHand +
            '}';
    }
}
