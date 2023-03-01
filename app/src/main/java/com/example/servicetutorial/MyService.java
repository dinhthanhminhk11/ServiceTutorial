package com.example.servicetutorial;

import static com.example.servicetutorial.MyApplication.CHANNEL_ID;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class MyService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("Minh", "onCreate");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    // nhảy vào đây khi gọi startService hoặc stopService hoặc gọi hàm stopSelf
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("Minh", "onStartCommand");
        Log.e("Minh", "data " + intent.getStringExtra("text"));
        senNotification(intent.getStringExtra("text"));
        return super.onStartCommand(intent, flags, startId);
    }

    private void senNotification(String text) {
        Intent intent = new Intent(this, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID).setContentTitle("Title Notition service").setContentText("text").setSmallIcon(R.mipmap.ic_launcher).setContentIntent(pendingIntent).build();

        //không có hàm này thì sẽ chạy 1 phút rồi tự ngừng
        startForeground(1, notification);

    }

    @Override
    public void onDestroy() {
        Log.e("Minh", "onDestroy");
        super.onDestroy();
    }
}
