package com.example.servicetutorial;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MusicBoundService extends Service {

    private MyBinder myBinder = new MyBinder();

    private MediaPlayer mediaPlayer;

    public class MyBinder extends Binder {
        MusicBoundService getMusicBoundService() {
            return MusicBoundService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("Minh_Log", "onCreate");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("Minh_Log", "onDestroy");
        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("Minh_Log", "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("Minh_Log", "onDestroy");
        // check pause
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void startMusic() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.filemusic);
        }
        mediaPlayer.start();
    }
}
