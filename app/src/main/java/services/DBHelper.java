package services;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "HOTEL_RESERVATION";
    private static final String TABLE_NAME = "ROOMS_TBL";
    private static final String COL_ROOM_ID = "ROOM_ID";
    private static final String COL_ROOM_NUMBER = "ROOM_NUMBER";
    private static final String COL_ROOM_IS_AVAIL = "ROOM_IS_AVAIL";
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




    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_ROOM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_ROOM_NUMBER + " INTEGER, "
                + COL_ROOM_IS_AVAIL + " INTEGER, "
                + COL_FULL_NAME + " TEXT, "
                + COL_CONTACT_NUMBER + " TEXT, "
                + COL_EMAIL_ADDRESS + " TEXT, "
                + COL_CHECK_IN + " TIMESTAMP, "
                + COL_CHECK_OUT + " TIMESTAMP, "
                + COL_NO_OF_GUEST + " INTEGER, "
                + COL_ROOM_TYPE_PREF + " TEXT, "
                + COL_REQUEST + " TEXT, "
                + COL_PAYMENT_INFO + " TEXT, "
                + COL_EMERGENCY_CONTACT + " TEXT)";
        sqLiteDatabase.execSQL(createTable);

        for(int i = 0; i < 10; i++){
            ContentValues v = new ContentValues();
            v.put(COL_ROOM_NUMBER, i + 1);
            v.put(COL_ROOM_IS_AVAIL, 1);
            sqLiteDatabase.insert(TABLE_NAME, null, v);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String dropTable = "DROP TABLE IF EXISTS " + TABLE_NAME;
        sqLiteDatabase.execSQL(dropTable);
        onCreate(sqLiteDatabase);
    }
}
