package com.example.servicetutorial;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY_COMPATIBILITY;
        /*
         * START_NOT_STICKY: Hệ thống bị kill , service này không đc khởi động lại (yêu cầu hệ thống không cần khởi động lại , ngay cả khi có đủ bộ nhớ
         *
         * START_STICKY: Hệ thống bị kill,
         *               yêu cầu hệ thống tạo một bản sao mới của service khi có đủ bộ nhớ ,
         *               nhưng các giá trị trước đố đều sẽ về null
         *               (ví dự load file tải 20% rồi nhưng bị kill khởi động lại sẽ bị tải lại từ đầu)
         *
         * START_REDELIVER_INTENT: yêu cầu hệ thống khởi động lại dịch vụ sau sự cố và cũng phân phối lại các intents có tịa thời điểm xảy ra sự cố
         *                         (ví dụ download file tải 20% bị kill thì khởi động lại sẽ tải tiếp)
         *
         * START_STICKY_COMPATIBILITY : giôgns thằng START_STICKY nhưng không chắc chắn, đảm bảo khời động lại service
         * */
    }
}
