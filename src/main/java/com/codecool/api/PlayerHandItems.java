package com.codecool.api;

import com.codecool.api.exceptions.SameItemException;

import java.io.Serializable;
import java.util.ArrayList;

public class PlayerHandItems implements Serializable {
    private ArrayList<Item> allItems;
    private ArrayList<Item> itemsInPlayerHand;

    public PlayerHandItems() {
        itemsInPlayerHand = new ArrayList<>();
        allItems = new ArrayList<>();
    }

    private void checkForSameItem(String nameOfItem) throws SameItemException {
        for (Item i : allItems) {
            if (i.getNameOfItem().equals(nameOfItem)) {
                throw new SameItemException("Theres no such item.");
            }
        }
    }

    void addItemToAllItems(Item item) throws SameItemException {
        checkForSameItem(item.getNameOfItem());
        allItems.add(item);
    }

    void pickUpItem(Item item) {
        itemsInPlayerHand.add(item);
    }

    void removeItemFromAllItems(Item item) {
        allItems.remove(item);
    }

    void putDownItem(Item item) {
        itemsInPlayerHand.remove(item);
    }

    ArrayList<Item> getItemsInPlayerHand() {
        return itemsInPlayerHand;
    }

    ArrayList<Item> getAllItems() {
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
