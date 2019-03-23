package com.example.mainactivity;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

public class EntryAdapter extends ResourceCursorAdapter {
    /** implement the adapter */
    public EntryAdapter(Context context, int layoutId,  Cursor cursor){
        super(context, layoutId, cursor);
    }

    /** take a view and fill the right elements with data from the cursor*/
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // retrieve the values
        String title = cursor.getString(cursor.getColumnIndex("title"));
        String date = cursor.getString(cursor.getColumnIndex("timestamp"));
        String mood = cursor.getString(cursor.getColumnIndex("mood"));
        // a default mood of happy with the same image
        if(mood == null){
            mood = "happy";
        }
        // implement the TextView's so they can be worked with
        TextView titleView = (TextView) view.findViewById(R.id.textViewTitle);
        TextView dateView = (TextView) view.findViewById(R.id.textViewTime);
        TextView moodView = (TextView) view.findViewById(R.id.textViewMood);
        // set the image to the right mood
        ImageView mood1 = view.findViewById(R.id.imageViewMood);
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
        // set the information in the TextViews
        titleView.setText(title);
        dateView.setText(date);
        moodView.setText(mood);

    }
}