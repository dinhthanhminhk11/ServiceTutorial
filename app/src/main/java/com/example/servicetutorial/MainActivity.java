package com.example.servicetutorial;

import static com.example.servicetutorial.MyService.ACTION_CLOSE;
import static com.example.servicetutorial.MyService.ACTION_PAUSE;
import static com.example.servicetutorial.MyService.ACTION_RESUME;
import static com.example.servicetutorial.MyService.ACTION_START;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.servicetutorial.model.Song;

public class MainActivity extends AppCompatActivity {
    private Button start;
    private Button stops;
    private LinearLayout contentLayoutMusic;
    private ImageView image;
    private TextView title;
    private ImageView play;
    private ImageView close;

    private Song song;
    private boolean isPlay;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // nơi nhận data
            Bundle bundle = intent.getExtras();
            if (bundle == null) {
                return;
            }
            song = (Song) bundle.get("objectSong");
            isPlay = bundle.getBoolean("status");
            int action = bundle.getInt("action_music");
            checkUI(action);
        }
    };

    private void checkUI(int action) {
        switch (action) {
            case ACTION_PAUSE:
                contentLayoutMusic.setVisibility(View.VISIBLE);
                break;
            case ACTION_RESUME:
                contentLayoutMusic.setVisibility(View.VISIBLE);
                break;
            case ACTION_CLOSE:
                contentLayoutMusic.setVisibility(View.GONE);
                break;
            case ACTION_START:
                contentLayoutMusic.setVisibility(View.VISIBLE);
                break;
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /// đăng kí lắng nghe BroadcastReceiver
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, new IntentFilter("send_data_to_activity"));

        start = (Button) findViewById(R.id.start);
        stops = (Button) findViewById(R.id.stops);
        contentLayoutMusic = (LinearLayout) findViewById(R.id.contentLayoutMusic);
        image = (ImageView) findViewById(R.id.image);
        title = (TextView) findViewById(R.id.title);
        play = (ImageView) findViewById(R.id.play);
        close = (ImageView) findViewById(R.id.close);

        start.setOnClickListener(v -> {
            Song song = new Song("Title", "Minh", R.drawable.musicimage, R.raw.filemusic);

            Intent intent = new Intent(this, MyService.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("song", song);
            intent.putExtras(bundle);

            startService(intent);
        });

        stops.setOnClickListener(v -> {
            stopService(new Intent(this, MyService.class));
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
    }

    private void sendDataToService(int action) { //send đén onStartCommand
        Intent intent = new Intent(this, MyService.class);
        intent.putExtra("action_music", action);
        startService(intent);
    }
}