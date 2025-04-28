package com.example.myapplication.Service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.example.myapplication.R;

public class MyMusicService extends Service {

    MediaPlayer mp; // MediaPlayer object to play the music

    // Default constructor
    public MyMusicService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // Create and initialize MediaPlayer with a music file from raw resources
        mp = MediaPlayer.create(this, R.raw.ineedmoreofyou);
        // Start playing the music
        mp.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Stop the music when the service is destroyed
        mp.stop();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // This service is not meant for binding, so throw an exception
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
