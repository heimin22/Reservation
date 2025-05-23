package com.example.ungabhotel.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.ungabhotel.model.Booking;
import com.example.ungabhotel.model.Room;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "HOTEL_RESERVATION";

    // ROOM TABLE
    private static final String ROOMS_TABLE  = "ROOMS_TBL";
    private static final String COL_ROOM_NUMBER = "ROOM_NUMBER";
    private static final String COL_ROOM_IS_AVAIL = "ROOM_IS_AVAIL";
    private static final String COL_ROOM_TYPE = "ROOM_TYPE";
    //BOOKING TABLE
    private static final String BOOKINGS_TABLE  = "BOOKINGS_TBL";
    private static final String COL_BOOKING_ID = "BOOKING_ID";
    private static final String COL_FULL_NAME = "FULL_NAME";
    private static final String COL_CONTACT_NUMBER = "CONTACT_NUMBER";
    private static final String COL_EMAIL_ADDRESS = "EMAIL_ADDRESS";
    private static final String COL_CHECK_IN = "CHECK_IN";
    private static final String COL_CHECK_OUT = "CHECK_OUT";
    private static final String COL_NO_OF_GUEST = "NO_OF_GUEST";
    private static final String COL_ROOM_TYPE_PREF = "ROOM_TYPE_PREF";
    private static final String COL_REQUEST = "REQUEST";
    private static final String COL_PAYMENT_INFO = "PAYMENT_INFO";
    private static final String COL_EMERGENCY_CONTACT= "EMERGENCY_CONTACT";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    public List<Room> getAvailableRooms(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Room> availableRooms = new ArrayList<>();
        String query = "SELECT * FROM " + ROOMS_TABLE + " WHERE " + COL_ROOM_IS_AVAIL  + " = ?";
        Cursor cursor = db.rawQuery(query, new String[1]);

        if(cursor.moveToFirst()){
            do{
                int roomNumber = Integer.parseInt(cursor.getString(1));
                boolean isAvail = Integer.parseInt(cursor.getString(2)) != 0;
                String roomType = cursor.getString(3);
                availableRooms.add(new Room(roomNumber, isAvail, roomType));
            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return availableRooms;
    }

    public List<Room> getAvailableRooms(Timestamp start, Timestamp end, String roomType) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Room> availableRooms = new ArrayList<>();

        String query = "SELECT * FROM " + ROOMS_TABLE + " WHERE " + COL_ROOM_TYPE + " = ? AND " + COL_ROOM_IS_AVAIL + " = 1";
        Cursor cursor = db.rawQuery(query, new String[]{roomType});

        if (cursor.moveToFirst()) {
            do {
                int roomNumber = cursor.getInt(0);
                boolean isAvail = cursor.getInt(1) != 0;
                String type = cursor.getString(2);

                if (!isRoomBooked(db, roomNumber, start, end)) {
                    availableRooms.add(new Room(roomNumber, isAvail, type));
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return availableRooms;
    }

    private boolean isRoomBooked(SQLiteDatabase db, int roomNumber, Timestamp start, Timestamp end) {
        String query = "SELECT * FROM " + BOOKINGS_TABLE +
                " WHERE " + COL_ROOM_TYPE_PREF + " = ? " +
                " AND ((" + COL_CHECK_IN + " < ? AND " + COL_CHECK_OUT + " > ?) " +
                " OR (" + COL_CHECK_IN + " >= ? AND " + COL_CHECK_IN + " < ?))";

        Cursor cursor = db.rawQuery(query, new String[]{
                String.valueOf(roomNumber),
                String.valueOf(end),
                String.valueOf(start),
                String.valueOf(start),
                String.valueOf(end)
        });

        boolean isBooked = cursor.getCount() > 0;
        cursor.close();
        return isBooked;
    }
    public List<Booking> getBookings(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM " + BOOKINGS_TABLE + " WHERE " + COL_BOOKING_ID + " != null";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String fullName = cursor.getString(1);
                String contactNumber = cursor.getString(2);
                String emailAddress = cursor.getString(3);
                Timestamp checkIn = Timestamp.valueOf(cursor.getString(4));
                Timestamp checkOut = Timestamp.valueOf(cursor.getString(5));
                int guests = Integer.parseInt(cursor.getString(6));
                String typePref = cursor.getString(7);
                String request = cursor.getString(8);
                String paymentInfo = cursor.getString(9);
                String emergencyContact = cursor.getString(10);

                bookings.add(new Booking(
                        id, fullName, contactNumber, emailAddress,
                        checkIn, checkOut, guests, typePref,
                        request, paymentInfo, emergencyContact));
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return bookings;
    }
    public List<Booking> getBookings(Room room){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM " + BOOKINGS_TABLE + " WHERE " + COL_BOOKING_ID + " != null";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String fullName = cursor.getString(1);
                String contactNumber = cursor.getString(2);
                String emailAddress = cursor.getString(3);
                Timestamp checkIn = Timestamp.valueOf(cursor.getString(4));
                Timestamp checkOut = Timestamp.valueOf(cursor.getString(5));
                int guests = Integer.parseInt(cursor.getString(6));
                String typePref = cursor.getString(7);
                String request = cursor.getString(8);
                String paymentInfo = cursor.getString(9);
                String emergencyContact = cursor.getString(10);

                bookings.add(new Booking(
                        id, room, fullName, contactNumber, emailAddress,
                        checkIn, checkOut, guests, typePref,
                        request, paymentInfo, emergencyContact));
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return bookings;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createRoomsTable = "CREATE TABLE " + ROOMS_TABLE + " ("
                + COL_ROOM_NUMBER + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_ROOM_IS_AVAIL + " INTEGER, "
                + COL_ROOM_TYPE + " TEXT);";
        sqLiteDatabase.execSQL(createRoomsTable);

        for(int i = 0; i < 10; i++){
            ContentValues v = new ContentValues();
            v.put(COL_ROOM_NUMBER, i + 1);
            v.put(COL_ROOM_IS_AVAIL, 1);
            if(i < 2) v.put(COL_ROOM_TYPE, "Premium Room");
            if(i < 4 && i >= 2) v.put(COL_ROOM_TYPE, "Deluxe Room");
            if(i < 6 && i >= 4) v.put(COL_ROOM_TYPE, "Executive Room");
            if(i < 8 && i >= 6) v.put(COL_ROOM_TYPE, "Regency Room");
            if(i >= 8) v.put(COL_ROOM_TYPE, "Royale Room");
            sqLiteDatabase.insert(ROOMS_TABLE, null, v);
        }

        String createBookingsTable = "CREATE TABLE " + BOOKINGS_TABLE + " ("
                + COL_BOOKING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_FULL_NAME + " TEXT, "
                + COL_CONTACT_NUMBER + " TEXT, "
                + COL_EMAIL_ADDRESS + " TEXT, "
                + COL_CHECK_IN + " TIMESTAMP, "
                + COL_CHECK_OUT + " TIMESTAMP, "
                + COL_NO_OF_GUEST + " INTEGER, "
                + COL_ROOM_TYPE_PREF + " TEXT, "
                + COL_REQUEST + " TEXT, "
                + COL_PAYMENT_INFO + " TEXT, "
                + COL_EMERGENCY_CONTACT + " TEXT);";
        sqLiteDatabase.execSQL(createBookingsTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String dropRoomsTable = "DROP TABLE IF EXISTS " + ROOMS_TABLE;
        sqLiteDatabase.execSQL(dropRoomsTable);

        String dropBookingsTable = "DROP TABLE IF EXISTS " + BOOKINGS_TABLE;
        sqLiteDatabase.execSQL(dropBookingsTable);

        onCreate(sqLiteDatabase);
    }
}
