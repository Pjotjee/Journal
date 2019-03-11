package com.example.mainactivity;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;
import java.sql.Timestamp;

public class EntryAdapter extends ResourceCursorAdapter {

    public EntryAdapter(Context context, Cursor cursor){
        super(context,R.layout.entry_row,cursor);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        String title = cursor.getString(cursor.getColumnIndex("title"));
        String date = cursor.getString(cursor.getColumnIndex("timestamp"));
        String mood = cursor.getString(cursor.getColumnIndex("mood"));
        if(mood == null){
            mood = "sad";
        }
        ImageView mood1 = view.findViewById(R.id.imageViewMood);
        System.out.println(mood);
        TextView titleView = (TextView) view.findViewById(R.id.textViewTitle);
        TextView dateView = (TextView) view.findViewById(R.id.textViewTime);
        TextView moodView = (TextView) view.findViewById(R.id.textViewMood);

        switch (mood){
            case "sad":
                mood1.setImageResource(R.drawable.sad);
                break;
            case "very sad":
                mood1.setImageResource(R.drawable.verysad);
                break;
            case "happy":
                mood1.setImageResource(R.drawable.happy);
                break;
            case "very happy":
                mood1.setImageResource(R.drawable.veryhappy);
                break;
        }

        titleView.setText(title);
        dateView.setText(date);
        moodView.setText(mood);

    }
}