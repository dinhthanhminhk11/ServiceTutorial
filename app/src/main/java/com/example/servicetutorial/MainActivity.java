package com.example.servicetutorial;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button start;
    private Button end;

    private MusicBoundService musicBoundService;
    private boolean isConnectedABoolean;//check connect
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            // khi kết nối thành công
            MusicBoundService.MyBinder myBinder = (MusicBoundService.MyBinder) iBinder;
            musicBoundService = myBinder.getMusicBoundService();
            musicBoundService.startMusic();
            isConnectedABoolean = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            /// khi nào kết nối thất bại
            isConnectedABoolean = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (Button) findViewById(R.id.start);
        end = (Button) findViewById(R.id.end);

        start.setOnClickListener(v -> {
            Intent intent = new Intent(this, MusicBoundService.class);
            bindService(intent, connection, Context.BIND_AUTO_CREATE); // gọi bindService là boundService
        });

        end.setOnClickListener(v -> {
            if (isConnectedABoolean) { // gọi bindService là boundService
                unbindService(connection);
                isConnectedABoolean = false;
            }
        });

    }
}