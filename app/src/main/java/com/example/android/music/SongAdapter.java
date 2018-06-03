package com.example.android.music;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * {@link SongAdapter} is an {@link SongAdapter} that can provide the layout for each list item
 * based on a data source, which is a list of {@link Song} objects.
 */
class SongAdapter extends ArrayAdapter<Song> {

    /**
     * Resource ID for the background color for this list of words
     */
    private final int mColorResourceId;

    /**
     * Create a new {@link SongAdapter} object.
     *
     * @param context         is the current context (i.e. Activity) that the adapter is being created in.
     * @param songs           is the list of {@link Song}s to be displayed.
     * @param colorResourceId is the resource ID for the background color for this list of songs
     */

    public SongAdapter(Activity context, ArrayList<Song> songs, int colorResourceId) {
        super(context, 0, songs);
        mColorResourceId = colorResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Song} object located at this position in the list
        Song currentSong = getItem(position);

        // Find the TextView in the list_item.xml layout with the song name
        TextView songTextView = listItemView.findViewById(R.id.song_text_view);

        // Get the current song object and set this text on the song TextView
        songTextView.setText(currentSong.getSongTitle());

        // Find the TextView in the list_item.xml layout with the album title
        TextView albumTextView = listItemView.findViewById(R.id.album_text_view);

        // Get the album title from the current album object and set this text on the album TextView
        albumTextView.setText(currentSong.getAlbumTitle());

        // Find the ImageView in the list_item.xml layout with the ID image.
        ImageView imageView = listItemView.findViewById(R.id.image);

        // Set the ImageView to the image resource specified in the current Song
        imageView.setImageResource(currentSong.getImageResourceId());

        // Set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.mainFrame);

        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);

        // Set the background color of the text container View
        textContainer.setBackgroundColor(color);

        // Return the list item layout (containing 2 TextViews and 1 ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}