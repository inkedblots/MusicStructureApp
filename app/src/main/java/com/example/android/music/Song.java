package com.example.android.music;


/**
 * {@link Song} represents the song or artist user wants to hear.
 * It contains a song title, album title, and an image.
 */

class Song {

    /**
     * Title for the song
     */
    private final String mSongTitle;

    /**
     * Image for the song
     */
    private final int mImageResourceId;

    /**
     * Album Title for the album
     */
    private final String mAlbumTitle;

    /**
     * Audio resource ID for the song
     */
    private final int mAudioResourceId;

    /**
     * Create a new object.
     *
     * @param AlbumTitle      is the name of the album that coincides with the song
     * @param SongTitle       is the title of the song
     * @param imageResourceId is the drawable resource ID for the image associated with the album
     * @param audioResourceId is the resource ID for the audio file associated with this song
     */
    Song(String SongTitle, String AlbumTitle, int imageResourceId, int audioResourceId) {
        mSongTitle = SongTitle;
        mAlbumTitle = AlbumTitle;
        mImageResourceId = imageResourceId;
        mAudioResourceId = audioResourceId;
    }

    /**
     * Get the Title for the song.
     */
    public String getSongTitle() {
        return mSongTitle;
    }

    /**
     * Get the Album title for the song.
     */
    public String getAlbumTitle() {
        return mAlbumTitle;
    }

    /**
     * Return the image resource ID of the song.
     */
    public int getImageResourceId() {
        return mImageResourceId;
    }

    /**
     * Return the audio resource ID of the song.
     */
    public int getAudioResourceId() {
        return mAudioResourceId;
    }
}



