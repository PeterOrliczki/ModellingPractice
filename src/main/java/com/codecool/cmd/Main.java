// java -cp target/JAR-1.0-SNAPSHOT.jar com.codecool.cmd.Main

// TODO
//  (rooms are also objects)
//  abstract Room class (childroom, adultroom)
//      String id
//      max itemList
//      addItem(abstract)
//      removeItem
//  House setActiveRoom(...)
//      Room[] (rooms)
//          1. add room
//          2. add item
//          3. switch room
//          4. put item
//      Room (active)
//      addRoom(Room(name, type)
//  Item[](items)
//  setActiveRoom(int index)
//  aRoom = room[]
//  addItemToRoom

package com.codecool.cmd;

import com.codecool.api.DownstairsRoom;
import com.codecool.api.Item;
import com.codecool.api.PlayerHandItems;
import com.codecool.api.RoomType;

import java.util.Scanner;

public class Main {
    private Scanner reader = new Scanner(System.in);
    private PlayerHandItems playerHandItems = new PlayerHandItems();
    private DownstairsRoom downstairsRoom = new DownstairsRoom();

    public static void main(String[] args) {
        Main main = new Main();
        while (true) {
            main.printMenu(main.printMainMenu());
            String userInput1 = main.getUserInput("Enter a number: ");
            if (userInput1.equals("1")) {
                main.itemMenu();
            } else if (userInput1.equals("2")) {
                main.playerHandMenu();
            } else if (userInput1.equals("3")) {
main.roomMenu();
            } else {

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

    private String[] printChildRoomsMenu(){
        String[] printing = {"Downstairs room menu", "Upstairsroom menu", "Loft menu"};
        return printing;
    }

    private void itemMenu() {
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
            System.out.println("Theres no such option.");
        }
    }

    private void playerHandMenu(){
        printMenu(printGeneralSubMenu());
        String userInput2 = getUserInput("Enter a number: ");
        if (userInput2.equals("1")){
            listPlayerHandItems();
        } else if (userInput2.equals("2")){
            addItemToPlayerHand();
        } else if (userInput2.equals("3")){
            removeItemFromPlayerHand();
        } else if (userInput2.equals("\n")) {
            System.out.println("You didn't enter anything, going back to main menu.");
        } else {
            System.out.println("Theres no such option.");
        }
    }

    private void roomMenu(){
        printMenu(printChildRoomsMenu());
        String userInput2 = getUserInput("Enter a number: ");
        if (userInput2.equals("1")){
            printMenu(printGeneralSubMenu());
            String userInput3 = getUserInput("Enter a number: ");
            if (userInput3.equals("1")){
                listDownstairsRoomItems();
            } else if (userInput3.equals("2")){
                addItemToDownstairsRoom();
            } else if (userInput3.equals("3")){
                removeItemFromDownstairsRoom();
            } else if (userInput3.equals("\n")) {
                System.out.println("You didn't enter anything, going back to main menu.");
            } else {
                System.out.println("Theres no such option.");
            }
        } else if (userInput2.equals("2")){

        } else if (userInput2.equals("3")){

        } else if (userInput2.equals("\n")) {
            System.out.println("You didn't enter anything, going back to main menu.");
        } else {
            System.out.println("Theres no such option.");
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

    private void addItemToAllItems() {
        String nameOfItem = getUserInput("What's the item?");
        RoomType typeOfItem = RoomType.valueOf(getUserInput("Where does it belong to?").toUpperCase());
        playerHandItems.addItemToAllItems(new Item(nameOfItem, typeOfItem));
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

    private void addItemToPlayerHand(){
        String nameOfItem = getUserInput("Enter the item you want to pick up: ");
        Item addingItem = searchForItemInPlayerHand(nameOfItem);
        playerHandItems.pickUpItem(addingItem);
    }

    private void removeItemFromPlayerHand(){
        String nameOfItem = getUserInput("Enter the item you want to pick up: ");
        Item removingItem = searchForItemInPlayerHand(nameOfItem);
        playerHandItems.putDownItem(removingItem);
    }

    private Item searchForItemInPlayerHand(String item){
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
    private void listDownstairsRoomItems(){
        if (downstairsRoom.getItemsInRoom().size() == 0) {
            System.out.println("The room is empty.");
        } else {
            int j = 1;
            for (Item i : downstairsRoom.getItemsInRoom()
            ) {
                System.out.println("Item number: " + j + ". \nItem Name: " + i.getNameOfItem() + "\n" +
                    "The room it belongs to: " + i.getTypeOfItem() + "\n");
                j++;
            }
        }
    }

    private void addItemToDownstairsRoom(){
        String nameOfItem = getUserInput("Enter the item you want to put down: ");
        Item addingItem = searchForItemInPlayerHand(nameOfItem);
        playerHandItems.putDownItem(addingItem);
        downstairsRoom.addItem(addingItem);
    }

    private void removeItemFromDownstairsRoom(){
        String nameOfItem = getUserInput("Enter the item you want to remove form here: ");
        Item removingItem = searchForItemInDownStairsRoom(nameOfItem);
        playerHandItems.pickUpItem(removingItem);
        downstairsRoom.removeItem(removingItem);
    }

    private Item searchForItemInDownStairsRoom(String item){
        Item searchedItem = null;
        for (Item i: playerHandItems.getItemsInPlayerHand()
             ) {
            if (i.getNameOfItem().equals(item)){
                searchedItem = i;
            }
        }
        if (searchedItem == null){
            System.out.println("There's no such item.");
        }
        return searchedItem;
    }

}
