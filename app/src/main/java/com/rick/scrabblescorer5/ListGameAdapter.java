package com.rick.scrabblescorer5;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class ListGameAdapter extends CursorAdapter {

    //private final Context mContext;

    public ListGameAdapter(Context context, Cursor c) {
        super(context, c, true);
    }


    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView tvPlayNumber = (TextView) view.findViewById(R.id.playnumber);
        TextView tvPlayer = (TextView) view.findViewById(R.id.player);
        TextView tvScore = (TextView) view.findViewById(R.id.score);
        // Extract properties from cursor
        int playNum = cursor.getInt(cursor.getColumnIndexOrThrow("playnumber"));
        String strPlayer = cursor.getString(cursor.getColumnIndexOrThrow("player"));
        int scoreit = cursor.getInt(cursor.getColumnIndexOrThrow("points"));
        // Populate fields with extracted properties
        String strPlayNum = String.valueOf(playNum);
        tvPlayer.setText(strPlayer);
        tvPlayNumber.setText(strPlayNum);
        tvScore.setText((String.valueOf(scoreit)));
        //tvBody.setText(body);
        //tvPriority.setText(String.valueOf(priority));
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.inner_listgame, parent, false);
    }
}
