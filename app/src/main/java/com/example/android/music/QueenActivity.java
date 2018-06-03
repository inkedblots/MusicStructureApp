package com.example.android.music;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class QueenActivity extends AppCompatActivity {

    /**
     * Handles playback of all the sound files
     */
    private MediaPlayer mMediaPlayer;

    /**
     * Handles audio focus when playing a sound file
     */
    private AudioManager mAudioManager;

    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    private final AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    switch (focusChange) {
                        case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                        case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                            // The AUDIOFOCUS_LOSS_TRANSIENT; lost audio focus
                            // The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK; our app is allowed to continue playing
                            // but at a lower volume. treat both the same

                            // Pause playback and reset player
                            mMediaPlayer.pause();
                            mMediaPlayer.seekTo(0);
                            break;
                        case AudioManager.AUDIOFOCUS_GAIN:
                            // The AUDIOFOCUS_GAIN; regained focus, resume playback.
                            mMediaPlayer.start();
                            break;
                        case AudioManager.AUDIOFOCUS_LOSS:
                            // The AUDIOFOCUS_LOSS; lost audio focus, Stop playback, clean up resources

                            releaseMediaPlayer();
                            break;
                    }
                }
            };

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private final MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_list);

        // Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // Create an array of songs
        final ArrayList<Song> songs = new ArrayList<>();

        //Song list
        songs.add(new Song("Bohemian Rhapsody", "A Night at the Opera", R.drawable.ic_qopera, R.raw.queen_bohemian));
        songs.add(new Song("Don\'t Stop Me Now", "Don\'t Stop Me Now", R.drawable.ic_qdontstop, R.raw.queen_dontstop));
        songs.add(new Song("Under Pressure", "Under Pressure", R.drawable.ic_qpressure, R.raw.queen_pressure));
        songs.add(new Song("Another One Bites the Dust", "The Game", R.drawable.ic_qgame, R.raw.queen_dust));
        songs.add(new Song("Somebody to Love", "A Day at the Races", R.drawable.ic_qraces, R.raw.queen_somebody));

        // Create an {@link ArrayAdapter}; creates layouts for each item in list.
        // Layout contains a single {@link TextView}, which adapter will display a single song.
        SongAdapter adapter = new SongAdapter(this, songs, R.color.artist_queen);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // {@link ListView} with the view ID list; declared in the song_list.xml layout file.
        ListView listView = findViewById(R.id.list);

        // Make the {@link ListView} use {@link SongAdapter}, so {@link ListView}
        // displays list items for each song in the list. By calling the setAdapter method on the
        // {@link ListView} object, pass in 1 argument, the {@link SongAdapter} with the variable
        // itemsAdapter.
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Release the media player to play a different song
                releaseMediaPlayer();

                // Get the {@link Song} object at the given position the user clicked on
                Song song = songs.get(position);

                // Request audio focus to play audio. App needs to play audio,
                // we will request audio focus with short amount of time with AUDIOFOCUS_GAIN_TRANSIENT
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // We have audio focus

                    // Create and setup the {@link MediaPlayer} for the audio resource associated
                    // with the current song
                    mMediaPlayer = MediaPlayer.create(QueenActivity.this, song.getAudioResourceId());

                    // Start the audio file
                    mMediaPlayer.start();

                    // Setup a listener on the media player, so that we can stop and release
                    // media player once song has finished playing.
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        // When activity is stopped, release media player resources, we won't be
        // playing any more songs.
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a song.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // we no longer need it.
            mMediaPlayer.release();

            // Set media player back to null.
            mMediaPlayer = null;

            // unregister the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}