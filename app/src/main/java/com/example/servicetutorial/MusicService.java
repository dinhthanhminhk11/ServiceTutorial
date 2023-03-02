package com.example.servicetutorial;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MusicService extends Service {
    public static final int MSG_SAY_HELLO = 1;
    private MediaPlayer mediaPlayer;
    private Messenger messenger;

    public class MyHandler extends Handler {
        private Context context;

        public MyHandler(Context context) {
            this.context = context.getApplicationContext();
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            // nhận dữ liệu
            if (msg.what == MSG_SAY_HELLO) {
                startMusic();
            }
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
        Log.e("Minh_Log", "onBind");
        messenger = new Messenger(new MyHandler(this));
        return messenger.getBinder();
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
        if(mediaPlayer !=null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void startMusic() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.filemusic);
        }
        mediaPlayer.start();
    }
}
