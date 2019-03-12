package com.codecool.api;

import com.codecool.api.exceptions.SameRoomException;

import java.util.ArrayList;
import java.util.List;

public class House {
    private ArrayList<DownstairsRoom> downstairsRooms;
    private ArrayList<UpstairsRoom> upstairsRooms;
    private ArrayList<Loft> lofts;
    private ArrayList<ArrayList> state;

    public House() {
        downstairsRooms = new ArrayList<>();
        upstairsRooms = new ArrayList<>();
        lofts = new ArrayList<>();
        state = new ArrayList<>();
    }

    public void addToState(ArrayList room) {
        state.add(room);
    }

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
    }

    public void iterateThroughRoomNames(String name) throws SameRoomException {
    }

    public List<Room> getRooms() {
        List<Room> allRooms = new ArrayList<>();
        allRooms.addAll(downstairsRooms);
        allRooms.addAll(upstairsRooms);
        allRooms.addAll(lofts);
        return allRooms;
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

    public ArrayList<ArrayList> getState() {
        return state;
    }
}
