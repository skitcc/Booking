package com.example.main.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final int  DATABASE_VERSION = 6;
    public static final String DATABASE_NAM = "nicDB";
    public static final String TABLE_CONTACTS = "contacts";
    public static final String TABLE_TITLE = "title";
    public static final String TABLE_BALETS = "balets";



    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_MAIL = "mail";
    public static final String KEY_TITLE = "title";
    public static final String KEY_DATE = "date";
    public static final String KEY_PLACE = "place";
    public static final String KEY_TITLE_BALET = "title_balet";
    public static final String KEY_DATE_BALET = "date_balet";




    public DBHelper(Context context) {
        super(context,DATABASE_NAM,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_CONTACTS + " ( " + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME  + " TEXT, " + KEY_MAIL + " TEXT" + ")");
        db.execSQL("CREATE TABLE " + TABLE_TITLE + " ( " + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT, " +  KEY_DATE  + " TEXT, " + KEY_PLACE + " TEXT" +")");
        db.execSQL("CREATE TABLE " + TABLE_BALETS + " ( " + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE_BALET + " TEXT, " +  KEY_DATE_BALET  + " TEXT, " + KEY_PLACE + " TEXT" + ")");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("drop table if exists " + TABLE_CONTACTS);
            db.execSQL("drop table if exists " + TABLE_TITLE);
            db.execSQL("drop table if exists " + TABLE_BALETS);
        } finally {
            onCreate(db);
        }

    }
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.setVersion(oldVersion);
    }


}
