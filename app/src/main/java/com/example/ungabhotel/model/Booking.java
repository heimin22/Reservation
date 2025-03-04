package com.example.ungabhotel.model;

import java.sql.Timestamp;

public class Booking {
    private int id;
    private Room room;
    private String fullName;
    private String contactNumber;
    private String emailAddress;
    private Timestamp checkIn;
    private Timestamp checkOut;
    private int guests;
    private String typePref;
    private String request;
    private String paymentInfo;
    private String emergencyContact;

    public Booking(int id, Room room, String fullName, String contactNumber, String emailAddress, Timestamp checkIn, Timestamp checkOut, int guests, String typePref, String request,
                   String paymentInfo, String emergencyContact)
    {
        this.id = id;
        this.room = room;
        this.fullName = fullName;
        this.contactNumber = contactNumber;
        this.emailAddress = emailAddress;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.guests = guests;
        this.typePref = typePref;
        this.request = request;
        this.paymentInfo = paymentInfo;
        this.emergencyContact = emergencyContact;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Timestamp getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Timestamp checkIn) {
        this.checkIn = checkIn;
    }

    public Timestamp getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Timestamp checkOut) {
        this.checkOut = checkOut;
    }

    public int getGuests() {
        return guests;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }

    public String getTypePref() {
        return typePref;
    }

    public void setTypePref(String typePref) {
        this.typePref = typePref;
    }

    public String getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(String paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }
}

