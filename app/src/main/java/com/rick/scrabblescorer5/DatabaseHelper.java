package com.rick.scrabblescorer5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "scrabblegames";
    private int currentGameTopId = 0;
    private int playnumber = 0;
    private SQLiteDatabase db;

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        db = getWritableDatabase();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the initial Game 0 row.
        db.execSQL(GameTop.CREATE_TABLE);
        db.execSQL(GamePlay.CREATE_TABLE);
        String id = GameTop.COLUMN_ID;
        String dt = GameTop.COLUMN_DATETIME;
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy-HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String df = formatter.format(date);
        ContentValues values = new ContentValues();
        values.put(id, 0);
        values.put(dt, df);
        db.insert(GameTop.TABLE_NAME, null, values);
    }

    public void newGame() {
        Cursor cur1 = db.rawQuery("select max(id) from GameTop", null);
        cur1.moveToFirst();
        int cid = cur1.getInt(0);
        currentGameTopId = cid + 1;
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy-HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String df = formatter.format(date);
        ContentValues values = new ContentValues();
        values.put("id", currentGameTopId);
        values.put("datetime", df);
        db.insert(GameTop.TABLE_NAME, null, values);
    }

    public int getCurrentGameTopId() {
        return currentGameTopId;
    }

    public void enterScore(String player, int score) {
        ContentValues values = new ContentValues();
        values.put(GamePlay.COLUMN_GAMEID, currentGameTopId);
        values.put(GamePlay.COLUMN_PLAYER, player);
        playnumber = playnumber + 1;
        values.put(GamePlay.COLUMN_PLAYNUMBER, playnumber);
        values.put(GamePlay.COLUMN_POINTS, score);
        db.insert(GamePlay.TABLE_NAME, null, values);
    }

    public Cursor listThisGame() {
        int i = 1;
        String queryCurrentGame = "select playnumber, player, points from GamePlay where gameid = " +
                String.valueOf(currentGameTopId) + " order by playnumber";
        Cursor cur = db.rawQuery(
                "select playnumber, player, points from GamePlay where gameid = 12 order by playnumber",
                null);
        String[] names = cur.getColumnNames();
        i = 2;
        return cur;
    }
    //    public long newGame() {
//        long new_game = 0;
//        ContentValues values = new ContentValues();
////        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cur1 = db.rawQuery("select count(*) from GameTop", null);
//        if(cur1.getInt(1) == 0) {
//            LocalDateTime.now();
//            String id = GameTop.getColumnId();
//            values.put("id", 0);
//            values.put("datetime");
////            db.insert(GameTop, "")
//        }
//        Cursor cur2 = db.rawQuery("select max(id) from GameTop;", null);
//        long cnt = cur2.getColumnCount();
//        String[] names = cur2.getColumnNames();
//        lastGame = cur2.getInt(1);
//        new_game = lastGame + 1;
//        LocalDateTime now = LocalDateTime.now();
////        db.execSQL("INSERT INTO table VALUES(...); ");now.toString()
////        db.execSQL("insert ");
//        // get last game number max id.
//        // put the hew game in the db, create the record.
//        // set last
//        return new_game;
//    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        Cursor cur1 = db.rawQuery("select max(id) from GameTop", null);
        cur1.moveToFirst();
        int cid = cur1.getInt(0);
        currentGameTopId = cid;
        cur1.close();
        int i = 1;
    }


////    public long insertScore(String note) {
//        // get writable database as we want to write data
////       `` SQLiteDatabase db = this.getWritableDatabase();
////
////        ContentValues values = new ContentValues();
//         `id` and `timestamp` will be inserted automatically.
//         no need to add them
////        values.put(Note.COLUMN_NOTE, note);
////
//         insert row
////        long id = db.insert(Note.TABLE_NAME, null, values);
////
//         close db connection
////        db.close();
////
//         return newly inserted row id
////        return id;
////    }


    @Override
    public String getDatabaseName() {
        return super.getDatabaseName();
    }


    @Override
    public SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }

    @Override
    public SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }

    @Override
    public synchronized void close() {
        super.close();
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
