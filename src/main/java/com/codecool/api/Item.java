package com.codecool.api;

import java.io.Serializable;

public class Item implements Serializable {
    private String nameOfItem;
    private RoomType typeOfItem;

    public Item(String nameOfItem, RoomType typeOfItem) {
        this.nameOfItem = nameOfItem;
        this.typeOfItem = typeOfItem;
    }

    public String getNameOfItem() {
        return nameOfItem;
    }

    public RoomType getTypeOfItem() {
        return typeOfItem;
    }

    @Override
    public String toString() {
        return "Item{" +
            "nameOfItem='" + nameOfItem + '\'' +
            ", typeOfItem=" + typeOfItem +
            '}';
    }
}
