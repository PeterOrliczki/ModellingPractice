// java -cp target/JAR-1.0-SNAPSHOT.jar com.codecool.cmd.Main

package com.codecool.cmd;

import com.codecool.api.House;
import com.codecool.api.PlayerHandItems;
import com.codecool.api.RoomLocation;
import com.codecool.api.exceptions.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.util.Scanner;

public class Main {
    //  1. private Map<RoomLocation, List<Room>> xxx;
    //  2. public void addRoom(Room room, RoomLocation roomLocation)
    //  List<Room> rooms = xxx.get(roomLocation);
    //  rooms.add(room);
    //  3. public ArrayList<DownstairsRoom> getDownstairsRooms() {
    //  public ArrayList<Room> getDownstairsRooms() {
    //  return xxx.get(RoomLocation.DOWNSTAIRS);

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
                    main.save();
                } else if (userInput1.equals("5")) {
                    main.load();
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
            } catch (DontBelongHereException e4) {
                System.out.println("This item can't be added to this room, it doesn't belong here.");
            } catch (CollapsingFromCarryingWayTooMuchException e5) {
                System.out.println("Your blood sugar plummeted from carrying way too many items, " +
                    "thus made you unable to do these tasks anymore, the program is quitting.");
                System.exit(0);
            } catch (IOException e6) {
                System.out.println("Serializer could not save");
            } catch (TransformerException e7) {
                System.out.println("Couldn't save state.");
            } catch (ParserConfigurationException e8) {
                System.out.println("Couldn't save state.");
            } catch (ClassNotFoundException e9) {
                System.out.println("Couldn't save state.");
            }
        }
    }

    // Larger menu methods
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
        String[] printing = {"Item menu", "Player hand menu", "Room menu", "Save", "Load"};
        return printing;
    }

    private String[] printGeneralSubMenu() {
        String[] printing = {"List items", "Add item", "Remove item"};
        return printing;
    }

    private String[] printChildRoomsMenu() {
        String[] printing = {"Downstairs room menu", "Upstairs room menu", "Loft menu", "Show all rooms"};
        return printing;
    }

    private String[] printItemTypeMenu() {
        String[] printing = {"Living room", "Kitchen", "Bedroom", "Bathroom"};
        return printing;
    }

    private String[] printRoomsChildMenu() {
        String[] printing = {"List room", "Add room", "Remove room", "Add an item to a room", "Remove an item from a room"};
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

    private void roomMenu() throws SameRoomException, RoomDoesntExistException, DontBelongHereException, CollapsingFromCarryingWayTooMuchException, TransformerException, ParserConfigurationException {
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
        } else if (userInput2.equals("\n")) {
            System.out.println("You didn't enter anything, going back to main menu.");
        } else {
            System.out.println("There's no such option.");
        }
    }

    private void roomsChildMenu(RoomLocation roomLocation) throws SameRoomException, RoomDoesntExistException, DontBelongHereException, CollapsingFromCarryingWayTooMuchException {
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
            removingRoomFromHouse(roomLocation);
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

    // Item menu methods
    private void listAllItems() {
        house.listAllItems(playerHandItems);
    }

    private void addItemToAllItems() throws SameItemException {
        String nameOfItem = getUserInput("What's the item?");
        printMenu(printItemTypeMenu());
        String typeOfItem = getUserInput("Where does it belong to?");
        house.addItemToAllItems(nameOfItem, typeOfItem, playerHandItems);
    }

    private void removeItemFromAllItems() {
        String removingItemName = getUserInput("What's the item you want to remove?");
        house.removeItemFromAllItems(removingItemName, playerHandItems);
    }


    // Player hand menu methods
    private void listPlayerHandItems() {
        house.listPlayerHandItems(playerHandItems);
    }

    private void addItemToPlayerHand() {
        String nameOfItem = getUserInput("Enter the item you want to pick up: ");
        house.addItemToPlayerHand(nameOfItem, playerHandItems);
    }

    private void removeItemFromPlayerHand() {
        String nameOfItem = getUserInput("Enter the item you want to pick up: ");
        house.removeItemFromPlayerHand(nameOfItem, playerHandItems);
    }

    // Rooms menu methods
    private void addItemToDownstairsRoom() throws RoomDoesntExistException, DontBelongHereException, CollapsingFromCarryingWayTooMuchException {
        String nameOfItem = getUserInput("Enter the item you want to put down: ");
        String roomToAddTo = getUserInput("Enter the room you want to put the item in: ");
        house.addItemToDownstairsRoom(nameOfItem, roomToAddTo, playerHandItems);
    }

    private void addItemToUpstairsRoom() throws RoomDoesntExistException, CollapsingFromCarryingWayTooMuchException {
        String nameOfItem = getUserInput("Enter the item you want to put down: ");
        String roomToAddTo = getUserInput("Enter the room you want to put the item in: ");
        house.addItemToUpstairsRoom(nameOfItem, roomToAddTo, playerHandItems);
    }

    private void addItemToLoft() throws RoomDoesntExistException, CollapsingFromCarryingWayTooMuchException {
        String nameOfItem = getUserInput("Enter the item you want to put down: ");
        String roomToAddTo = getUserInput("Enter the room you want to put the item in: ");
        house.addItemToLoft(nameOfItem, roomToAddTo, playerHandItems);
    }

    private void removeItemFromDownstairsRoom() throws RoomDoesntExistException {
        String nameOfItem = getUserInput("Enter the item you want to remove from here: ");
        String roomToRemoveFrom = getUserInput("Enter the room you want to remove the item from: ");
        house.removeItemFromDownstairsRoom(nameOfItem, roomToRemoveFrom, playerHandItems);
    }

    private void removeItemFromUpstairsRoom() throws RoomDoesntExistException {
        String nameOfItem = getUserInput("Enter the item you want to remove from here: ");
        String roomToRemoveFrom = getUserInput("Enter the room you want to remove the item from: ");
        house.removeItemFromUpstairsRoom(nameOfItem, roomToRemoveFrom, playerHandItems);
    }

    private void removeItemFromLoft() throws RoomDoesntExistException {
        String nameOfItem = getUserInput("Enter the item you want to remove from here: ");
        String roomToRemoveFrom = getUserInput("Enter the room you want to remove the item from: ");
        house.removeItemFromLoft(nameOfItem, roomToRemoveFrom, playerHandItems);
    }

    // house menu methods
    private void listRooms() {
        house.listRooms();
    }


    private void listDownstairsRoomsByLocation() {
        house.listDownstairsRoomsByLocation();
    }

    private void listUpstairsRoomsByLocation() {
        house.listUpstairsRoomsByLocation();
    }

    private void listLoftByLocation() {
        house.listLoftByLocation();
    }

    private void addRoomToHouse(RoomLocation roomLocation) throws SameRoomException {
        String userInput1 = getUserInput("Enter the name of the new room: ");
        house.addRoomToHouse(userInput1, roomLocation);
    }

    private void removingRoomFromHouse(RoomLocation roomLocation) {
        String userInput1 = getUserInput("Enter the name of the room you want to remove: ");
        house.removingRoomFromHouse(userInput1, roomLocation);
    }

    // serializer methods
    private void save() throws IOException {
        FileOutputStream fileOut = new FileOutputStream("./serializing.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(playerHandItems);
        out.writeObject(house);
        out.close();
        fileOut.close();
        System.out.println("\nSerialized data is saved in serializing.ser.\n");
    }

    private void load() throws IOException, ClassNotFoundException {
        house = null;
        playerHandItems = null;
        FileInputStream fileIn = new FileInputStream("./serializing.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        playerHandItems = (PlayerHandItems) in.readObject();
        house = (House) in.readObject();
        in.close();
        fileIn.close();
        System.out.println("\nSerialized data is loaded from serializing.ser.\n");
    }
}
