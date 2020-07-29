package com.rick.scrabblescorer5;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.GridView;

import androidx.annotation.Nullable;

public class ListGameActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview_gamelayout);
        GridView lgame = findViewById(R.id.list_game_gridview);
        //findViewById(R.id.list_layout).requestLayout();
        DatabaseHelper dbh = new DatabaseHelper(this, DatabaseHelper.DATABASE_NAME,
                null, DatabaseHelper.DATABASE_VERSION);
        SQLiteDatabase db = dbh.getWritableDatabase();
        String queryCurrentGame = "select id as _id, playnumber, player, points from GamePlay where gameid = " +
                String.valueOf(dbh.getCurrentGameTopId()) + " order by playnumber";
        Cursor cur = db.rawQuery(queryCurrentGame, null);
        ListGameAdapter lga = new ListGameAdapter(this, null);
        lga.changeCursor(cur);
        lgame.setAdapter(lga);
        int i = 1;
    }
}
