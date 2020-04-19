package com.example.witsbluelibrary.induce;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;


//后台开门
public class BleBackstageScanService extends Service {

    private NotificationManager notificationManager;
    private NotificationCompat.Builder builder;
    private Notification notification;
    private final static int notifyId = 0x023;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);

    }


    public void Notification(String aMessage) {
        String wit = "witsystem";
        String channelID = "1";
        String channelName = "witsystem";
        if (builder == null) {
            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            builder = new NotificationCompat.Builder(this, wit);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);
                channel.enableLights(false);
                channel.enableVibration(false);
                channel.setVibrationPattern(new long[]{0});
                channel.setSound(null, null);
                builder.setChannelId(channelID);
                notificationManager.createNotificationChannel(channel);
            } else {
                builder.setVibrate(new long[]{0L});
            }
            builder.setDefaults(NotificationCompat.FLAG_ONLY_ALERT_ONCE);
            /*builder.setSmallIcon(R.drawable.)
                    .setContentTitle(aMessage)
                    .setContentText("等待设备中.."); */
            builder.setContentTitle(aMessage)
                    .setContentText("等待设备中..");
            notification = builder.build();
            notification.flags = Notification.FLAG_ONGOING_EVENT;
            //notificationManager.notify(notifyId, notification);
            startForeground(notifyId, notification);
        }

    }

}
