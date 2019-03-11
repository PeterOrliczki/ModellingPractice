package com.codecool.api;

import java.util.ArrayList;

public class House {
    private ArrayList<Room> rooms;
    private ArrayList<DownstairsRoom> downstairsRooms;
    private ArrayList<UpstairsRoom> upstairsRooms;
    private ArrayList<Loft> lofts;
    private ArrayList<Room> state;

    public House() {
        rooms = new ArrayList<>();
        downstairsRooms = new ArrayList<>();
        upstairsRooms = new ArrayList<>();
        lofts = new ArrayList<>();
        state = new ArrayList<>();
    }

//    public void addToState(){
//        state.add()
//    }

    public void addRoom(Room room, RoomLocation roomLocation) throws SameRoomException {
        iterateThroughRoomNames(room.getName());
        if (roomLocation.equals(RoomLocation.DOWNSTAIRS)) {
            downstairsRooms.add((DownstairsRoom) room);
        } else if (roomLocation.equals(RoomLocation.UPSTAIRS)) {
            upstairsRooms.add((UpstairsRoom) room);
        } else if (roomLocation.equals(RoomLocation.LOFT)) {
            lofts.add((Loft) room);
        }
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

    public ArrayList<Room> getState() {
        return state;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public ArrayList<DownstairsRoom> getDownstairsRooms() {
        return downstairsRooms;
    }

    public ArrayList<UpstairsRoom> getUpstairsRooms() {
        return upstairsRooms;
    }

    public ArrayList<Loft> getLofts() {
        return lofts;
    }
}
