package com.example.familyafterwork0;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    //    The database is named faw5
    private static final String DATABASE_NAME = "faw5.db";
    private static final int DATABASE_VERSION = 1;

    //    The first table is named table_events
    public static final String TABLE_EVENTS = "table_events";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "name";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_MEMBER = "guardian";
    public static final String COLUMN_KID = "child";

    public static final String TABLE_FAMILY = "table_family";
    public static final String COLUMN_FAMILY_ID = "_id";
    public static final String COLUMN_MEMBER_NAME = "member_name";
    public static final String COLUMN_ROLE = "role";
    public static final String COLUMN_IMAGE = "image";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EVENT_TABLE = "CREATE TABLE " + TABLE_EVENTS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_TIME + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_MEMBER + " TEXT, " +
                COLUMN_KID + " TEXT)";
        String CREATE_FAMILY_TABLE = "CREATE TABLE " + TABLE_FAMILY + " (" +
                COLUMN_FAMILY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_MEMBER_NAME + " TEXT, " +
                COLUMN_ROLE + " TEXT, " +
                COLUMN_IMAGE + " TEXT)";
        db.execSQL(CREATE_EVENT_TABLE);
        db.execSQL(CREATE_FAMILY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAMILY);
        onCreate(db);
    }

    public void addEvent(Event event){
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, event.getTitle());
        values.put(COLUMN_DATE, event.getDate());
        values.put(COLUMN_TIME, event.getTime());
        values.put(COLUMN_DESCRIPTION, event.getDescr());
        values.put(COLUMN_MEMBER, event.getMember());
        values.put(COLUMN_KID, event.getKid());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_EVENTS, null, values);
        db.close();
    }

    //Method to find an event in the DB based on its ID
    public Event findEvent(String eventID) {
        String query = "SELECT * FROM " + TABLE_EVENTS + " WHERE " +
                COLUMN_ID + " = '" + eventID + "'";
        SQLiteDatabase db = this. getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Event event = new Event();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            event.setID(Integer.parseInt(cursor.getString(0)));
            event.setTitle(cursor.getString(1));
            event.setDate(cursor.getString(2));
            event.setTime(cursor.getString(3));
            event.setDescr(cursor.getString(4));
            event.setMember(cursor.getString(5));
            event.setKid(cursor.getString(6));
            cursor.close();
        } else {
            event = null;
        }
        db.close();
        return event;
    }
    public Family findMember(String memberID) {
        String query = "SELECT * FROM " + TABLE_FAMILY + " WHERE " +
                COLUMN_FAMILY_ID + " = '" + memberID + "'";
        SQLiteDatabase db = this. getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Family family = new Family();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            family.setID(Integer.parseInt(cursor.getString(0)));
            family.setName(cursor.getString(1));
            family.setRole(cursor.getString(2));
            family.setImg(cursor.getString(3));

            cursor.close();
        } else {
            family = null;
        }
        db.close();
        return family;
    }

    //Method to delete an event in the DB based on its ID
    public void deleteEvent(String eventID) {
        Event event = findEvent(eventID);
        if (event != null){
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_EVENTS, COLUMN_ID + " = ?",
                    new String[] {String.valueOf(event.getID()) });
            db.close();
        }
    }
    //Method to delete an event in the DB based on its ID
    public void deleteMember(String memberID) {
        Family family = findMember(memberID);
        if (family != null){
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_FAMILY, COLUMN_FAMILY_ID + " = ?",
                    new String[] {String.valueOf(family.getID()) });
            db.close();
        }
    }


    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_EVENTS + " ORDER BY " + COLUMN_DATE + " DESC, " + COLUMN_TIME + " DESC", null);
        if (cursor.moveToFirst()) {
            do {
                Event event = new Event(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MEMBER)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_KID))
                );
                events.add(event);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return events;
    }

    public List<Family> getAllMembers() {
        List<Family> familys = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_FAMILY, null);
        if (cursor.moveToFirst()) {
            do {
                Family family = new Family(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_FAMILY_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MEMBER_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ROLE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGE))
                );
                familys.add(family);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return familys;
    }
}
