package com.example.witsbluelibrary.induce;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.example.witsbluelibrary.R;

import java.util.List;


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
    public void onCreate() {
        super.onCreate();
        Notification("感应开锁已经开启");
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("启动服务", "启动服务" + startId);
        // Notification("测试");
        //扫描到蓝牙设备信息
        //获取返回的错误码
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.O) {
            return START_STICKY;
        }

        int errorCode = intent.getIntExtra(BluetoothLeScanner.EXTRA_ERROR_CODE, -1);
        //List<ScanResult> scanResults = (List<ScanResult>) (intent.getSerializableExtra(BluetoothLeScanner.EXTRA_LIST_SCAN_RESULT));

        //获取到的蓝牙设备的回调类型
        int callbackType = intent.getIntExtra(BluetoothLeScanner.EXTRA_CALLBACK_TYPE, -1);//ScanSettings.CALLBACK_TYPE_*
        if (errorCode == -1) {
            //扫描到蓝牙设备信息
            List<ScanResult> scanResults = (List<ScanResult>) (intent.getSerializableExtra(BluetoothLeScanner.EXTRA_LIST_SCAN_RESULT));
            if (scanResults != null) {
                for (ScanResult result : scanResults) {
                    // list.add(result);
                    BluetoothDevice device = result.getDevice();
                    Log.e("启动服务2", "获得设备" + device.getName());
                    //  openDevice(device, result.getRssi());
                }
            }
        }


        return START_STICKY;

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
            builder.setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(aMessage)
                    .setContentText("等待设备中..");

            notification = builder.build();
            notification.flags = Notification.FLAG_ONGOING_EVENT;
            //notificationManager.notify(notifyId, notification);
            startForeground(notifyId, notification);
        }

    }

}
