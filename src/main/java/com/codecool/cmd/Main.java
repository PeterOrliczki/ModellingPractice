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

import com.codecool.api.Item;
import com.codecool.api.PlayerHandItems;
import com.codecool.api.RoomType;

import java.util.Scanner;

public class Main {
    private Scanner reader = new Scanner(System.in);
    private PlayerHandItems playerHandItems = new PlayerHandItems();

    public static void main(String[] args) {
        Main main = new Main();
        while (true) {
            main.printMenu(main.printMainMenu());
            String userInput1 = main.getUserInput("Enter a number: ");
            if (userInput1.equals("1")) {
                main.itemMenu();
            } else if (userInput1.equals("2")) {

            } else if (userInput1.equals("3")) {

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

    private String[] printItemMenu() {
        String[] printing = {"List items", "Add item", "Remove item"};
        return printing;
    }

    private void itemMenu() {
        printMenu(printItemMenu());
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

    // Item menu methods
    private void listAllItems() {
        if (playerHandItems.getAllItems().size() == 0) {
            System.out.println("There are no items.");
        } else {
            for (Item i : playerHandItems.getAllItems()
            ) {
                System.out.println("\nItem name: " + i.getNameOfItem() + "\nThe room the item belongs to: " + i.getTypeOfItem() + "\n");
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

    private Item searchItemInAllItems(String name) {
        Item searchedItem = null;
        for (Item i : playerHandItems.getAllItems()
        ) {
            if (i.getNameOfItem().equals(name)) {
                searchedItem = i;
            }
        }
        if (searchedItem == null) {
            System.out.println("Item not found.");
        }
        return searchedItem;
    }

}
