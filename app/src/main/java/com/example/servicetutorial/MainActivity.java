package com.example.servicetutorial;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button start;
    private Button stop;

    private Messenger messenger;
    private boolean isCheckConnect;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            messenger = new Messenger(iBinder);
            isCheckConnect = true;
            sendMessagePlayMusic();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isCheckConnect = false;
            messenger = null;
        }
    };

    private void sendMessagePlayMusic() {
        Message message = Message.obtain(null, MusicService.MSG_SAY_HELLO, 0, 0);
        try {
            // send dữ liệu
            messenger.send(message);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);

        start.setOnClickListener(v -> {
            Intent intent = new Intent(this, MusicService.class);
            bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        });

        stop.setOnClickListener(v -> {
            if (isCheckConnect) {
                unbindService(serviceConnection);
                isCheckConnect = false;
            }
        });

    }
}