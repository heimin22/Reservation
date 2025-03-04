package com.example.ungabhotel.model;

import java.sql.Timestamp;

public class Booking {
    private int id;
    private Room room;
    private String fullName;
    private String contactNumber;
    private String emailAddress;
    private Timestamp checkIn;
    private Timestamp checkout;
    private int guests;
    private String typePref;
    private String request;
    private String paymentInfo;
    private String emergencyContact;
}

