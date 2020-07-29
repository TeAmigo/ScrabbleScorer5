package com.rick.scrabblescorer5;

// TODO: Get the List layout looking good.
// data base ops.

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.text.SpannableStringBuilder;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class MainActivity extends AppCompatActivity {

    CustomKeyboard mCustomKeyboard;
    TextView mActiveValue;
    EditText jeScore;
    EditText rScore;
    public static DatabaseHelper db;
    public String PLAYER1 = "JoEllen";
    public String PLAYER2 = "Rick";

    public DatabaseHelper getDb() {
        return db;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCustomKeyboard = new CustomKeyboard(this, R.id.keyboardview, R.xml.hexkbd);
        mActiveValue = (TextView) findViewById(R.id.activeValue);
        jeScore = findViewById(R.id.JoEllenTotalScore);
        rScore = findViewById(R.id.RickTotalScore);
        mActiveValue.setText(R.string.initval, TextView.BufferType.EDITABLE);
//        String ret = getScreenResolution(this);
//        View focusCurrent = MainActivity.getCurrentFocus();
        mCustomKeyboard.registerTextView(R.id.activeValue);
        db = new DatabaseHelper(this, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION);
        db.newGame();
//        db.newGame();
        int i = 1;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.newgame) {
            db.newGame();
            return newgame();
        } else if (id == R.id.listgame) {
            Intent intent = new Intent(this, ListGameActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.exportDB) {
            exportDB();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void exportDB(){
        File sd = Environment.getExternalStorageDirectory();
        File data = Environment.getDataDirectory();
        FileChannel source=null;
        FileChannel destination=null;
        String currentDBPath = "/data/"+ "com.rick.scrabblescorer5" +"/databases/"+ DatabaseHelper.DATABASE_NAME;
        // /data/data/com.rick.scrabblescorer5/databases
        String backupDBPath = DatabaseHelper.DATABASE_NAME;
        File currentDB = new File(data, currentDBPath);
        File backupDB = new File(sd, backupDBPath);
        try {
            source = new FileInputStream(currentDB).getChannel();
            destination = new FileOutputStream(backupDB).getChannel();
            destination.transferFrom(source, 0, source.size());
            source.close();
            destination.close();
            Toast.makeText(this, "DB Exported!", Toast.LENGTH_LONG).show();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private boolean newgame() {
        mActiveValue.setText(R.string.initval, TextView.BufferType.EDITABLE);
        jeScore.setText(R.string.initval, TextView.BufferType.EDITABLE);
        rScore.setText(R.string.initval, TextView.BufferType.EDITABLE);
        return true;
    }


    private static String getScreenResolution(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        return "{" + width + "," + height + "}";
    }

    public void onClickToJoEllen(View v) {
        // Code here executes on main thread after user presses button
        SpannableStringBuilder activeValueSpan = (SpannableStringBuilder) mActiveValue.getText();
        String activeValueStr = activeValueSpan.toString();
        SpannableStringBuilder scoreSpan = (SpannableStringBuilder) jeScore.getText();
        String scoreVal1 = scoreSpan.toString();
        int addNum = Integer.parseInt(activeValueStr);
        int oldNum = Integer.parseInt(scoreVal1);
        int newNum = oldNum + addNum;
        int i = 1;
        jeScore.setText(Integer.toString(newNum));
        mActiveValue.setText(R.string.initval);
        db.enterScore(PLAYER1, addNum);
    }

    public void onClickToRick(View v) {
        // Code here executes on main thread after user presses button
        SpannableStringBuilder activeValueSpan = (SpannableStringBuilder) mActiveValue.getText();
        String activeValueStr = activeValueSpan.toString();
        SpannableStringBuilder scoreSpan = (SpannableStringBuilder) rScore.getText();
        String scoreVal1 = scoreSpan.toString();
        int addNum = Integer.parseInt(activeValueStr);
        int oldNum = Integer.parseInt(scoreVal1);
        int newNum = oldNum + addNum;
        int i = 1;
        rScore.setText(Integer.toString(newNum));
        mActiveValue.setText(R.string.initval);
        db.enterScore(PLAYER2, addNum);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        //If needed, code to restore variables coming back can be stored here.
        super.onRestoreInstanceState(savedInstanceState);
        int i = 1;
    }


}
