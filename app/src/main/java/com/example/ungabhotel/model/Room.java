package com.example.ungabhotel.model;

public class Room {
    private int roomNumber;
    private boolean isAvail;

    public Room(int roomNumber, boolean isAvail) {
        this.roomNumber = roomNumber;
        this.isAvail = isAvail;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public boolean isAvail() {
        return isAvail;
    }

    public void setAvail(boolean avail) {
        isAvail = avail;
    }
}