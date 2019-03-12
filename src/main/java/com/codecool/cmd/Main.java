// java -cp target/JAR-1.0-SNAPSHOT.jar com.codecool.cmd.Main

package com.codecool.cmd;

import com.codecool.api.*;
import com.codecool.api.exceptions.RoomDoesntExistException;
import com.codecool.api.exceptions.SameItemException;
import com.codecool.api.exceptions.SameRoomException;

import java.util.Scanner;

public class Main {
    private Scanner reader = new Scanner(System.in);
    private PlayerHandItems playerHandItems = new PlayerHandItems();
    private House house = new House();

    public static void main(String[] args) {
        Main main = new Main();
        while (true) {
            try {
                main.printMenu(main.printMainMenu());
                String userInput1 = main.getUserInput("Enter a number: ");
                if (userInput1.equals("1")) {
                    main.itemMenu();
                } else if (userInput1.equals("2")) {
                    main.playerHandMenu();
                } else if (userInput1.equals("3")) {
                    main.roomMenu();
                } else if (userInput1.equals("4")) {
                    main.roomPrintTest();
                } else if (userInput1.equals("\n")) {
                    System.out.println("You didn't enter anything, going back to main menu.");
                } else {
                    System.out.println("There's no such option.");
                }
            } catch (SameItemException e) {
                System.out.println("Item already exists.");
            } catch (SameRoomException e2) {
                System.out.println("Room already exists.");
            } catch (RoomDoesntExistException e3) {
                System.out.println("Room doesn't exists.");
            }
        }
    }

    // Menu methods
    private String getUserInput(String text) {
        System.out.println(text);
        return reader.nextLine();
    }

    private void printMenu(String[] printing) {
        for (int i = 0; i < printing.length; i++) {
            System.out.println((i + 1) + ". " + printing[i]);
        }
    }

    private String[] printMainMenu() {
        String[] printing = {"Item menu", "Player hand menu", "Room menu"};
        return printing;
    }

    private String[] printGeneralSubMenu() {
        String[] printing = {"List items", "Add item", "Remove item"};
        return printing;
    }

    private String[] printChildRoomsMenu() {
        String[] printing = {"Downstairs room menu", "Upstairs room menu", "Loft menu", "Show all rooms", "State"};
        return printing;
    }

    private String[] printItemTypeMenu() {
        String[] printing = {"Living room", "Kitchen", "Bedroom", "Bathroom"};
        return printing;
    }

    private String[] printRoomsChildMenu() {
        String[] printing = {"List rooms", "Add room", "Remove room", "Add an item to a room", "Remove an item from a room"};
        return printing;
    }

    private void itemMenu() throws SameItemException {
        printMenu(printGeneralSubMenu());
        String userInput2 = getUserInput("Enter a number: ");
        if (userInput2.equals("1")) {
            listAllItems();
        } else if (userInput2.equals("2")) {
            addItemToAllItems();
        } else if (userInput2.equals("3")) {
            removeItemFromAllItems();
        } else if (userInput2.equals("\n")) {
            System.out.println("You didn't enter anything, going back to main menu.");
        } else {
            System.out.println("There's no such option.");
        }
    }

    private void playerHandMenu() {
        printMenu(printGeneralSubMenu());
        String userInput2 = getUserInput("Enter a number: ");
        if (userInput2.equals("1")) {
            listPlayerHandItems();
        } else if (userInput2.equals("2")) {
            addItemToPlayerHand();
        } else if (userInput2.equals("3")) {
            removeItemFromPlayerHand();
        } else if (userInput2.equals("\n")) {
            System.out.println("You didn't enter anything, going back to main menu.");
        } else {
            System.out.println("There's no such option.");
        }
    }

    private void roomMenu() throws SameRoomException, RoomDoesntExistException {
        printMenu(printChildRoomsMenu());
        String userInput2 = getUserInput("Enter a number: ");
        if (userInput2.equals("1")) {
            roomsChildMenu(RoomLocation.DOWNSTAIRS);
        } else if (userInput2.equals("2")) {
            roomsChildMenu(RoomLocation.UPSTAIRS);
        } else if (userInput2.equals("3")) {
            roomsChildMenu(RoomLocation.LOFT);
        } else if (userInput2.equals("4")) {
            listRooms();
        } else if (userInput2.equals("5")) {
            state();
        } else if (userInput2.equals("\n")) {
            System.out.println("You didn't enter anything, going back to main menu.");
        } else {
            System.out.println("There's no such option.");
        }
    }

    private void roomsChildMenu(RoomLocation roomLocation) throws SameRoomException, RoomDoesntExistException {
        printMenu(printRoomsChildMenu());
        String userInput3 = getUserInput("Enter a number: ");
        if (userInput3.equals("1")) {
            if (roomLocation.equals(RoomLocation.DOWNSTAIRS)) {
                listDownstairsRoomsByLocation();
            } else if (roomLocation.equals(RoomLocation.UPSTAIRS)) {
                listUpstairsRoomsByLocation();
            } else if (roomLocation.equals(RoomLocation.LOFT)) {
                listLoftByLocation();
            }
        } else if (userInput3.equals("2")) {
            addRoomToHouse(roomLocation);
        } else if (userInput3.equals("3")) {
            removingRoomFromHouse();
        } else if (userInput3.equals("4")) {
            if (roomLocation.equals(RoomLocation.DOWNSTAIRS)) {
                addItemToDownstairsRoom();
            } else if (roomLocation.equals(RoomLocation.UPSTAIRS)) {
                addItemToUpstairsRoom();
            } else if (roomLocation.equals(RoomLocation.LOFT)) {
                addItemToLoft();
            }
        } else if (userInput3.equals("5")) {
            if (roomLocation.equals(RoomLocation.DOWNSTAIRS)) {
                removeItemFromDownstairsRoom();
            } else if (roomLocation.equals(RoomLocation.UPSTAIRS)) {
                removeItemFromUpstairsRoom();
            } else if (roomLocation.equals(RoomLocation.LOFT)) {
                removeItemFromLoft();
            }
        } else if (userInput3.equals("\n")) {
            System.out.println("You didn't enter anything, going back to main menu.");
        } else {
            System.out.println("There's no such option.");
        }
    }

    private void houseMenu() {
        printMenu(printGeneralSubMenu());
        String userInput1 = getUserInput("Enter a number: ");
        if (userInput1.equals("1")) {

        } else if (userInput1.equals("2")) {

        } else if (userInput1.equals("3")) {

        } else if (userInput1.equals("\n")) {
            System.out.println("You didn't enter anything, going back to main menu.");
        } else {
            System.out.println("There's no such option.");
        }
    }

    // Item menu methods
    private void listAllItems() {
        if (playerHandItems.getAllItems().size() == 0) {
            System.out.println("There are no items.");
        } else {
            int j = 1;
            for (Item i : playerHandItems.getAllItems()
            ) {
                System.out.println("Item number: " + j + ". \nName: " + i.getNameOfItem() + "\n" +
                    "The room it belongs to: " + i.getTypeOfItem() + "\n");
                j++;
            }
        }
    }

    private void addItemToAllItems() throws SameItemException {
        String nameOfItem = getUserInput("What's the item?");
        printMenu(printItemTypeMenu());
        String typeOfItem = getUserInput("Where does it belong to?");
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

    private void removeItemFromAllItems() {
        String removingItemName = getUserInput("What's the item you want to remove?");
        Item removingItem = searchItemInAllItems(removingItemName);
        playerHandItems.removeItemFromAllItems(removingItem);
    }

    private Item searchItemInAllItems(String item) {
        Item searchedItem = null;
        for (Item i : playerHandItems.getAllItems()
        ) {
            if (i.getNameOfItem().equals(item)) {
                searchedItem = i;
            }
        }
        if (searchedItem == null) {
            System.out.println("Item not found.");
        }
        return searchedItem;
    }

    // Player hand menu methods
    private void listPlayerHandItems() {
        if (playerHandItems.getItemsInPlayerHand().size() == 0) {
            System.out.println("There's nothing in your hand");
        } else {
            int j = 1;
            for (Item i : playerHandItems.getItemsInPlayerHand()
            ) {
                System.out.println("Item number: " + j + ". \nItem Name: " + i.getNameOfItem() + "\n" +
                    "The room it belongs to: " + i.getTypeOfItem() + "\n");
                j++;
            }
        }
    }

    private void addItemToPlayerHand() {
        String nameOfItem = getUserInput("Enter the item you want to pick up: ");
        Item addingItem = searchForItemInPlayerHand(nameOfItem);
        playerHandItems.pickUpItem(addingItem);
    }

    private void removeItemFromPlayerHand() {
        String nameOfItem = getUserInput("Enter the item you want to pick up: ");
        Item removingItem = searchForItemInPlayerHand(nameOfItem);
        playerHandItems.putDownItem(removingItem);
    }

    private Item searchForItemInPlayerHand(String item) {
        Item searchedItem = null;
        for (Item i : playerHandItems.getAllItems()
        ) {
            if (i.getNameOfItem().equals(item)) {
                searchedItem = i;
            }
        }
        if (searchedItem == null) {
            System.out.println("Item not found.");
        }
        return searchedItem;
    }

    // Rooms menu methods
    private void addItemToDownstairsRoom() throws RoomDoesntExistException {
        String nameOfItem = getUserInput("Enter the item you want to put down: ");
        Item addingItem = searchForItemInPlayerHand(nameOfItem);
        String roomToAddTo = getUserInput("Enter the room you want to put the item in: ");
        int addingToRoomIndex = searchForDownstairsRoomInHouse(roomToAddTo);
        playerHandItems.putDownItem(addingItem);
        house.getDownstairsRooms().get(addingToRoomIndex).addItem(addingItem);
    }

    private void addItemToUpstairsRoom() throws RoomDoesntExistException {
        String nameOfItem = getUserInput("Enter the item you want to put down: ");
        Item addingItem = searchForItemInPlayerHand(nameOfItem);
        String roomToAddTo = getUserInput("Enter the room you want to put the item in: ");
        int addingToRoomIndex = searchForUpstairsRoomInHouse(roomToAddTo);
        playerHandItems.putDownItem(addingItem);
        house.getUpstairsRooms().get(addingToRoomIndex).addItem(addingItem);
    }

    private void addItemToLoft() throws RoomDoesntExistException {
        String nameOfItem = getUserInput("Enter the item you want to put down: ");
        Item addingItem = searchForItemInPlayerHand(nameOfItem);
        String roomToAddTo = getUserInput("Enter the room you want to put the item in: ");
        int addingToRoomIndex = searchForLoftInHouse(roomToAddTo);
        playerHandItems.putDownItem(addingItem);
        house.getLofts().get(addingToRoomIndex).addItem(addingItem);
    }

    private void removeItemFromDownstairsRoom() throws RoomDoesntExistException {
        String nameOfItem = getUserInput("Enter the item you want to remove from here: ");
        Item removingItem = searchForItemInRoom(nameOfItem);
        String roomToRemoveFrom = getUserInput("Enter the room you want to put the item in: ");
        int removingFromRoomIndex = searchForDownstairsRoomInHouse(roomToRemoveFrom);
        playerHandItems.pickUpItem(removingItem);
        house.getDownstairsRooms().get(removingFromRoomIndex).removeItem(removingItem);
    }

    private void removeItemFromUpstairsRoom() throws RoomDoesntExistException {
        String nameOfItem = getUserInput("Enter the item you want to remove from here: ");
        Item removingItem = searchForItemInRoom(nameOfItem);
        String roomToRemoveFrom = getUserInput("Enter the room you want to put the item in: ");
        int removingFromRoomIndex = searchForUpstairsRoomInHouse(roomToRemoveFrom);
        playerHandItems.pickUpItem(removingItem);
        house.getUpstairsRooms().get(removingFromRoomIndex).removeItem(removingItem);
    }

    private void removeItemFromLoft() throws RoomDoesntExistException {
        String nameOfItem = getUserInput("Enter the item you want to remove from here: ");
        Item removingItem = searchForItemInRoom(nameOfItem);
        String roomToRemoveFrom = getUserInput("Enter the room you want to put the item in: ");
        int removingFromRoomIndex = searchForLoftInHouse(roomToRemoveFrom);
        playerHandItems.pickUpItem(removingItem);
        house.getLofts().get(removingFromRoomIndex).removeItem(removingItem);
    }

    private Item searchForItemInRoom(String item) {
        Item searchedItem = null;
        for (Item i : house.getRooms().get(0).getItemsInRoom()
        ) {
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
        for (Room i : house.getDownstairsRooms()
        ) {
            if (i.getName().equals(roomName)) {
                searched = i;
            }
        }
        if (searched == null) {
            throw new RoomDoesntExistException();
        }
        return house.getDownstairsRooms().indexOf(searched);
    }

    private int searchForUpstairsRoomInHouse(String roomName) throws RoomDoesntExistException {
        Room searched = null;
        for (Room i : house.getUpstairsRooms()
        ) {
            if (i.getName().equals(roomName)) {
                searched = i;
            }
        }
        if (searched == null) {
            throw new RoomDoesntExistException();
        }
        return house.getUpstairsRooms().indexOf(searched);
    }

    private int searchForLoftInHouse(String roomName) throws RoomDoesntExistException {
        Room searched = null;
        for (Room i : house.getLofts()
        ) {
            if (i.getName().equals(roomName)) {
                searched = i;
            }
        }
        if (searched == null) {
            throw new RoomDoesntExistException();
        }
        return house.getLofts().indexOf(searched);
    }

    // house menu methods
    private void listRooms() {
        System.out.println(house.getDownstairsRooms());
        System.out.println(house.getUpstairsRooms());
        System.out.println(house.getLofts());
    }

    private void state() {
        house.addToState(house.getDownstairsRooms());
        house.addToState(house.getUpstairsRooms());
        house.addToState(house.getLofts());
        System.out.println(house.getState());
    }


    private void listDownstairsRoomsByLocation() {
        if (house.getDownstairsRooms().size() == 0) {
            System.out.println("There aren't any rooms.");
        } else {
            int j = 1;
            for (Item i : house.getDownstairsRooms().get(0).getItemsInRoom()
            ) {
                System.out.println("Items in the room: \nItem number: " + j + ". \nItem name: " + i.getNameOfItem() +
                    "\nWhere it belongs: " + i.getTypeOfItem());
                j++;
            }
        }
    }

    private void listUpstairsRoomsByLocation() {
        if (house.getUpstairsRooms().size() == 0) {
            System.out.println("There aren't any rooms.");
        } else {
            int j = 1;
            for (Item i : house.getUpstairsRooms().get(0).getItemsInRoom()
            ) {
                System.out.println("Items in the room: \nItem number: " + j + ". \nItem name: " + i.getNameOfItem() +
                    "\nWhere it belongs: " + i.getTypeOfItem());
                j++;
            }
        }
    }

    private void listLoftByLocation() {
        if (house.getLofts().size() == 0) {
            System.out.println("There aren't any rooms.");
        } else {
            int j = 1;
            for (Item i : house.getLofts().get(0).getItemsInRoom()
            ) {
                System.out.println("Items in the room: \nItem number: " + j + ". \nItem name: " + i.getNameOfItem() +
                    "\nWhere it belongs: " + i.getTypeOfItem());
                j++;
            }
        }
    }

    private void addRoomToHouse(RoomLocation roomLocation) throws SameRoomException {
        String userInput1 = getUserInput("Enter the name of the new room: ");
        if (roomLocation.equals(RoomLocation.DOWNSTAIRS)) {
            house.addRoom(new DownstairsRoom(userInput1, RoomLocation.DOWNSTAIRS), roomLocation);
        } else if (roomLocation.equals(RoomLocation.UPSTAIRS)) {
            house.addRoom(new UpstairsRoom(userInput1, RoomLocation.UPSTAIRS), roomLocation);
        } else if (roomLocation.equals(RoomLocation.LOFT)) {
            house.addRoom(new Loft(userInput1, RoomLocation.LOFT), roomLocation);
        }
    }

    private void removingRoomFromHouse() {
        String userInput1 = getUserInput("Enter the item you want to remove: ");
        Room deletingRoom = searchRoomInHouse(userInput1);
        house.deleteRoom(deletingRoom);
    }

    private Room searchRoomInHouse(String name) {
        Room searched = null;
        for (Room i : house.getRooms()
        ) {
            if (i.getName().equals(name)) {
                searched = i;
            }
        }
        if (searched == null) {
            System.out.println("Item not found.");
        }
        return null;
    }

    private void roomPrintTest() {
        System.out.println(house.getUpstairsRooms());
    }
}

