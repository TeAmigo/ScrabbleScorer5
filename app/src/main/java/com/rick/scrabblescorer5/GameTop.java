package com.rick.scrabblescorer5;

import android.os.Build;

//import androidx.annotation.RequiresApi;

//@RequiresApi(api = Build.VERSION_CODES.O)
public class GameTop {
    public static final String TABLE_NAME = "GameTop";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DATETIME = "datetime";




    private int id;
    private String datetime;

    public static String getTableName() {
        return TABLE_NAME;
    }

    public static String getColumnDatetime() {
        return COLUMN_DATETIME;
    }

    public static String getColumnId() {
        return COLUMN_ID;
    }

    public static String getCreateTable() {
        return CREATE_TABLE;
    }

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_DATETIME + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";

    public GameTop() {
    }

    public GameTop(int id, String timestamp) {
        this.id = id;
        this.datetime = timestamp;
    }

    public int getId() {
        return id;
    }

    public String getTimestamp() {
        return datetime;
    }

    public void setDateTime(String dt) {
        this.datetime = dt;
    }

    public String getDateTime() {
        return datetime;
    }

    public void setId(int id) {
        this.id = id;
    }

 }
