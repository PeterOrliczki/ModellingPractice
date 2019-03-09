package com.codecool.api;

import java.util.ArrayList;

public class PlayerHandItems{
    private ArrayList<Item> allItems;
    private ArrayList<Item> itemsInPlayerHand;

    public PlayerHandItems() {
        itemsInPlayerHand = new ArrayList<>();
        allItems = new ArrayList<>();
    }

    public void addItemToAllItems(Item item){
        allItems.add(item);
    }

    public void pickUpItem(Item item){
        itemsInPlayerHand.add(item);
    }

    public void removeItemFromAllItems(Item item){
        allItems.remove(item);
    }

    public void putDownItem(Item item){
        itemsInPlayerHand.remove(item);
    }

    public ArrayList<Item> getItemsInPlayerHand() {
        return itemsInPlayerHand;
    }

    public ArrayList<Item> getAllItems() {
        return allItems;
    }

    @Override
    public String toString() {
        return "PlayerHandItems{" +
            "allItems=" + allItems +
            ", itemsInPlayerHand=" + itemsInPlayerHand +
            '}';
    }
}
