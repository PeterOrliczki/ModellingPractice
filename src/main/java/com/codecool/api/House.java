package com.codecool.api;

import java.util.ArrayList;

public class House {
    private ArrayList<Room> rooms;

    public House() {
        rooms = new ArrayList<>();
    }

    public void addRoom(Room room) throws SameRoomException {
        iterateThroughRoomNames(room.getName());
        rooms.add(room);
    }

    public void deleteRoom(Room room) {
        rooms.remove(room);
    }

    public void iterateThroughRoomNames(String name) throws SameRoomException {
        for (Room room : rooms) {
            if (room.getName().equals(name)) {
                throw new SameRoomException();
            }
        }

    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }
}
