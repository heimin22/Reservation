package com.example.ungabhotel.model;

public class Room {
    private int roomNumber;
    private boolean isAvail;
    private String roomType;

    public Room(int roomNumber, boolean isAvail, String roomType) {
        this.roomNumber = roomNumber;
        this.isAvail = isAvail;
        this.roomType = roomType;
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

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
}