package com.codecool.api;

import com.codecool.api.exceptions.SameRoomException;

import java.util.ArrayList;
import java.util.List;

public class House {
    private ArrayList<DownstairsRoom> downstairsRooms;
    private ArrayList<UpstairsRoom> upstairsRooms;
    private ArrayList<Loft> loftsRooms;
    private ArrayList<ArrayList> state;

    public House() {
        downstairsRooms = new ArrayList<>();
        upstairsRooms = new ArrayList<>();
        loftsRooms = new ArrayList<>();
        state = new ArrayList<>();
    }

    public void addToState(ArrayList room) {
        state.add(room);
    }

    public void addRoom(Room room, RoomLocation roomLocation) throws SameRoomException {
        if (roomLocation.equals(RoomLocation.DOWNSTAIRS)) {
            downstairsRooms.add((DownstairsRoom) room);
        } else if (roomLocation.equals(RoomLocation.UPSTAIRS)) {
            upstairsRooms.add((UpstairsRoom) room);
        } else if (roomLocation.equals(RoomLocation.LOFT)) {
            loftsRooms.add((Loft) room);
        }
    }

    public void deleteRoom(Room room, RoomLocation roomLocation) {
        if (roomLocation.equals(RoomLocation.DOWNSTAIRS)) {
            downstairsRooms.remove(room);
        } else if (roomLocation.equals(RoomLocation.UPSTAIRS)) {
            upstairsRooms.remove(room);
        } else if (roomLocation.equals(RoomLocation.LOFT)) {
            loftsRooms.remove(room);
        }
    }


    public List<Room> getRooms() {
        List<Room> allRooms = new ArrayList<>();
        allRooms.addAll(downstairsRooms);
        allRooms.addAll(upstairsRooms);
        allRooms.addAll(loftsRooms);
        return allRooms;
    }

    public ArrayList<DownstairsRoom> getDownstairsRooms() {
        return downstairsRooms;
    }

    public ArrayList<UpstairsRoom> getUpstairsRooms() {
        return upstairsRooms;
    }

    public ArrayList<Loft> getLoftsRooms() {
        return loftsRooms;
    }

    public ArrayList<ArrayList> getState() {
        return state;
    }
}
