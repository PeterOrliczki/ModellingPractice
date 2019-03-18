package com.codecool.api;

import com.codecool.api.exceptions.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class House implements Serializable {
    private ArrayList<DownstairsRoom> downstairsRooms;
    private ArrayList<UpstairsRoom> upstairsRooms;
    private ArrayList<Loft> loftsRooms;
//    private ArrayList<ArrayList> state;

    public House() {
        downstairsRooms = new ArrayList<>();
        upstairsRooms = new ArrayList<>();
        loftsRooms = new ArrayList<>();
//        state = new ArrayList<>();
    }

    public void listAllItems(PlayerHandItems playerHandItems) {
        if (playerHandItems.getAllItems().size() == 0) {
            System.out.println("There are no items.");
        } else {
            int j = 1;
            for (Item i : playerHandItems.getAllItems()) {
                System.out.println("Item number: " + j + ". \nName: " + i.getNameOfItem() + "\n" +
                    "The room it belongs to: " + i.getTypeOfItem() + "\n");
                j++;
            }
        }
    }

    public void addItemToAllItems(String nameOfItem, String typeOfItem, PlayerHandItems playerHandItems) throws SameItemException {
        if (typeOfItem.equals("1")) {
            playerHandItems.addItemToAllItems(new Item(nameOfItem, RoomType.LIVINGROOM));
        } else if (typeOfItem.equals("2")) {
            playerHandItems.addItemToAllItems(new Item(nameOfItem, RoomType.KITCHEN));
        } else if (typeOfItem.equals("3")) {
            playerHandItems.addItemToAllItems(new Item(nameOfItem, RoomType.BEDROOM));
        } else if (typeOfItem.equals("4")) {
            playerHandItems.addItemToAllItems(new Item(nameOfItem, RoomType.BATHROOM));
        } else if (typeOfItem.equals("\n")) {
            System.out.println("You didn't enter anything, going back to main menu.");
        } else {
            System.out.println("There's no such option.");
        }
    }

    public void removeItemFromAllItems(String removingItemName, PlayerHandItems playerHandItems) {
        Item removingItem = searchItemInAllItems(removingItemName, playerHandItems);
        playerHandItems.removeItemFromAllItems(removingItem);
    }

    private Item searchItemInAllItems(String item, PlayerHandItems playerHandItems) {
        Item searchedItem = null;
        for (Item i : playerHandItems.getAllItems()) {
            if (i.getNameOfItem().equals(item)) {
                searchedItem = i;
            }
        }
        if (searchedItem == null) {
            System.out.println("Item not found.");
        }
        return searchedItem;
    }

    public void listPlayerHandItems(PlayerHandItems playerHandItems) {
        if (playerHandItems.getItemsInPlayerHand().size() == 0) {
            System.out.println("There's nothing in your hand");
        } else {
            int j = 1;
            for (Item i : playerHandItems.getItemsInPlayerHand()) {
                System.out.println("Item number: " + j + ". \nItem Name: " + i.getNameOfItem() + "\n" +
                    "The room it belongs to: " + i.getTypeOfItem() + "\n");
                j++;
            }
        }
    }

    public void addItemToPlayerHand(String nameOfItem, PlayerHandItems playerHandItems) {
        Item addingItem = searchForItemInPlayerHand(nameOfItem, playerHandItems);
        playerHandItems.pickUpItem(addingItem);
    }

    public void removeItemFromPlayerHand(String nameOfItem, PlayerHandItems playerHandItems) {
        Item removingItem = searchForItemInPlayerHand(nameOfItem, playerHandItems);
        playerHandItems.putDownItem(removingItem);
    }

    private Item searchForItemInPlayerHand(String item, PlayerHandItems playerHandItems) {
        Item searchedItem = null;
        for (Item i : playerHandItems.getAllItems()) {
            if (i.getNameOfItem().equals(item)) {
                searchedItem = i;
            }
        }
        if (searchedItem == null) {
            System.out.println("Item not found.");
        }
        return searchedItem;
    }

    public void addItemToDownstairsRoom(String nameOfItem, String roomToAddTo, PlayerHandItems playerHandItems) throws RoomDoesntExistException, DontBelongHereException, CollapsingFromCarryingWayTooMuchException {
        Item addingItem = searchForItemInPlayerHand(nameOfItem, playerHandItems);
        int addingToRoomIndex = searchForDownstairsRoomInHouse(roomToAddTo);
        playerHandItems.putDownItem(addingItem);
        this.getDownstairsRooms().get(addingToRoomIndex).addItem(addingItem, playerHandItems);
    }

    public void addItemToUpstairsRoom(String nameOfItem, String roomToAddTo, PlayerHandItems playerHandItems) throws RoomDoesntExistException, CollapsingFromCarryingWayTooMuchException {
        Item addingItem = searchForItemInPlayerHand(nameOfItem, playerHandItems);
        int addingToRoomIndex = searchForUpstairsRoomInHouse(roomToAddTo);
        playerHandItems.putDownItem(addingItem);
        this.getUpstairsRooms().get(addingToRoomIndex).addItem(addingItem, playerHandItems);
    }

    public void addItemToLoft(String nameOfItem, String roomToAddTo, PlayerHandItems playerHandItems) throws RoomDoesntExistException, CollapsingFromCarryingWayTooMuchException {
        Item addingItem = searchForItemInPlayerHand(nameOfItem, playerHandItems);
        int addingToRoomIndex = searchForLoftInHouse(roomToAddTo);
        this.getLoftsRooms().get(addingToRoomIndex).addItem(addingItem, playerHandItems);
        playerHandItems.putDownItem(addingItem);
    }

    public void removeItemFromDownstairsRoom(String nameOfItem, String roomToRemoveFrom, PlayerHandItems playerHandItems) throws RoomDoesntExistException {
        Item removingItem = searchForItemInRoom(nameOfItem);
        int removingFromRoomIndex = searchForDownstairsRoomInHouse(roomToRemoveFrom);
        this.getDownstairsRooms().get(removingFromRoomIndex).removeItem(removingItem);
        playerHandItems.pickUpItem(removingItem);
    }

    public void removeItemFromUpstairsRoom(String nameOfItem, String roomToRemoveFrom, PlayerHandItems playerHandItems) throws RoomDoesntExistException {
        Item removingItem = searchForItemInRoom(nameOfItem);
        int removingFromRoomIndex = searchForUpstairsRoomInHouse(roomToRemoveFrom);
        this.getUpstairsRooms().get(removingFromRoomIndex).removeItem(removingItem);
        playerHandItems.pickUpItem(removingItem);
    }

    public void removeItemFromLoft(String nameOfItem, String roomToRemoveFrom, PlayerHandItems playerHandItems) throws RoomDoesntExistException {
        Item removingItem = searchForItemInRoom(nameOfItem);
        int removingFromRoomIndex = searchForLoftInHouse(roomToRemoveFrom);
        playerHandItems.pickUpItem(removingItem);
        this.getLoftsRooms().get(removingFromRoomIndex).removeItem(removingItem);
    }

    private Item searchForItemInRoom(String item) {
        Item searchedItem = null;
        for (Item i : this.getRooms().get(0).getItemsInRoom()) {
            if (i.getNameOfItem().equals(item)) {
                searchedItem = i;
            }
        }
        if (searchedItem == null) {
            System.out.println("There's no such item.");
        }
        return searchedItem;
    }

    private int searchForDownstairsRoomInHouse(String roomName) throws RoomDoesntExistException {
        Room searched = null;
        for (Room i : this.getDownstairsRooms()) {
            if (i.getName().equals(roomName)) {
                searched = i;
            }
        }
        if (searched == null) {
            throw new RoomDoesntExistException();
        }
        return this.getDownstairsRooms().indexOf(searched);
    }

    private int searchForUpstairsRoomInHouse(String roomName) throws RoomDoesntExistException {
        Room searched = null;
        for (Room i : this.getUpstairsRooms()) {
            if (i.getName().equals(roomName)) {
                searched = i;
            }
        }
        if (searched == null) {
            throw new RoomDoesntExistException();
        }
        return this.getUpstairsRooms().indexOf(searched);
    }

    private int searchForLoftInHouse(String roomName) throws RoomDoesntExistException {
        Room searched = null;
        for (Room i : this.getLoftsRooms()) {
            if (i.getName().equals(roomName)) {
                searched = i;
            }
        }
        if (searched == null) {
            throw new RoomDoesntExistException();
        }
        return this.getLoftsRooms().indexOf(searched);
    }

    public void listRooms() {
        System.out.println(this.getDownstairsRooms());
        System.out.println(this.getUpstairsRooms());
        System.out.println(this.getLoftsRooms());
    }

//        private void state() {
//        System.out.println(house.getRooms());
//    }

    public void listDownstairsRoomsByLocation() {
        if (this.getDownstairsRooms().size() == 0) {
            System.out.println("There aren't any rooms.");
        } else {
            int j = 1;
            for (Room i : this.getDownstairsRooms()) {
                System.out.println("Room listing: \nRoom number: " + j + ". \nRoom name: " + i.getName() +
                    "\nThe items it has: " + i.getItemsInRoom());
                j++;
            }
        }
    }

    public void listUpstairsRoomsByLocation() {
        if (this.getUpstairsRooms().size() == 0) {
            System.out.println("There aren't any rooms.");
        } else {
            int j = 1;
            for (Room i : this.getUpstairsRooms()) {
                System.out.println("Room listing: \nRoom number: " + j + ". \nRoom name: " + i.getName() +
                    "\nThe items it has: " + i.getItemsInRoom());
                j++;
            }
        }
    }

    public void listLoftByLocation() {
        if (this.getLoftsRooms().size() == 0) {
            System.out.println("There aren't any rooms.");
        } else {
            int j = 1;
            for (Room i : this.getLoftsRooms()) {
                System.out.println("Room listing: \nRoom number: " + j + ". \nRoom name: " + i.getName() +
                    "\nThe items it has: " + i.getItemsInRoom());
                j++;
            }
        }
    }

    public void addRoomToHouse(String userInput1, RoomLocation roomLocation) throws SameRoomException {
        if (roomLocation.equals(RoomLocation.DOWNSTAIRS) && searchRoomInHouse(userInput1) == null) {
            this.addRoom(new DownstairsRoom(userInput1, RoomLocation.DOWNSTAIRS), roomLocation);
        } else if (roomLocation.equals(RoomLocation.UPSTAIRS) && searchRoomInHouse(userInput1) == null) {
            this.addRoom(new UpstairsRoom(userInput1, RoomLocation.UPSTAIRS), roomLocation);
        } else if (roomLocation.equals(RoomLocation.LOFT) && searchRoomInHouse(userInput1) == null) {
            this.addRoom(new Loft(userInput1, RoomLocation.LOFT), roomLocation);
        } else {
            throw new SameRoomException();
        }
    }

    public void removingRoomFromHouse(String userInput1, RoomLocation roomLocation) {
        if (searchRoomInHouse(userInput1) != null) {
            this.deleteRoom(searchRoomInHouse(userInput1), roomLocation);
        }
    }

    private Room searchRoomInHouse(String name) {
        Room searched = null;
        for (Room i : this.getRooms()) {
            if (i.getName().equals(name)) {
                searched = i;
            }
        }
        return searched;
    }

    private void addRoom(Room room, RoomLocation roomLocation) {
        if (roomLocation.equals(RoomLocation.DOWNSTAIRS)) {
            downstairsRooms.add((DownstairsRoom) room);
        } else if (roomLocation.equals(RoomLocation.UPSTAIRS)) {
            upstairsRooms.add((UpstairsRoom) room);
        } else if (roomLocation.equals(RoomLocation.LOFT)) {
            loftsRooms.add((Loft) room);
        }
    }

    private void deleteRoom(Room room, RoomLocation roomLocation) {
        if (roomLocation.equals(RoomLocation.DOWNSTAIRS)) {
            downstairsRooms.remove(room);
        } else if (roomLocation.equals(RoomLocation.UPSTAIRS)) {
            upstairsRooms.remove(room);
        } else if (roomLocation.equals(RoomLocation.LOFT)) {
            loftsRooms.remove(room);
        }
    }

    private List<Room> getRooms() {
        List<Room> allRooms = new ArrayList<>();
        allRooms.addAll(downstairsRooms);
        allRooms.addAll(upstairsRooms);
        allRooms.addAll(loftsRooms);
        return allRooms;
    }

    private ArrayList<DownstairsRoom> getDownstairsRooms() {
        return downstairsRooms;
    }

    private ArrayList<UpstairsRoom> getUpstairsRooms() {
        return upstairsRooms;
    }

    private ArrayList<Loft> getLoftsRooms() {
        return loftsRooms;
    }

//    public ArrayList<ArrayList> getState() {
//        return state;
//    }
}
