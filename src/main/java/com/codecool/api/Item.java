package com.codecool.api;

public class Item{
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
