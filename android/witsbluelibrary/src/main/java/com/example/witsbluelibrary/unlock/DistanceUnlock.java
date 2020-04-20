package com.example.witsbluelibrary.unlock;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.example.witsbluelibrary.induce.Induce;

import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Math.abs;

/**
 * 距离开锁判断设备的距离
 */
public class DistanceUnlock {

    private static DistanceUnlock distanceUnlock;

    private Context context;

    private BluetoothGatt gatt;

    private Timer timer;

    private TimerTask timerTask;

    public static DistanceUnlock instance(Context context) {
        if (distanceUnlock == null) {
            synchronized (Induce.class) {
                if (distanceUnlock == null) {
                    distanceUnlock = new DistanceUnlock();
                    distanceUnlock.context = context;

                }
            }
        }
        return distanceUnlock;
    }


    ///连接设备进行距离判断
    public void connectDeviceRanging(ScanResult scanResult) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return;
        }
        gatt = scanResult.getDevice().connectGatt(context, false, new BluetoothGattCallback() {
            @Override
            public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
                super.onConnectionStateChange(gatt, status, newState);
                Log.e("启动服务", "连接状态：" + status + ":::" + newState);
                if ( newState == BluetoothProfile.STATE_CONNECTED) {
                    readRssi(gatt);
                } else {
                    stopReadRssi();
                }
            }

            @Override
            public void onServicesDiscovered(BluetoothGatt gatt, int status) {
                super.onServicesDiscovered(gatt, status);
            }

            @Override
            public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
                super.onCharacteristicRead(gatt, characteristic, status);
            }

            @Override
            public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
                super.onCharacteristicWrite(gatt, characteristic, status);
            }

            @Override
            public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
                super.onCharacteristicChanged(gatt, characteristic);
            }

            @Override
            public void onReadRemoteRssi(BluetoothGatt gatt, int rssi, int status) {
                super.onReadRemoteRssi(gatt, rssi, status);
                Log.e("启动服务", "设备信号" + rssi);
             /*  double d = 10^((abs(rssi) - 70) / (10 * 2));
                Log.e("启动服务", "距离" +  d);*/
            }
        });
    }

    //不断读取信号
    private void readRssi(final BluetoothGatt gatt) {
        if (timerTask != null) return;
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                gatt.readRemoteRssi();
            }
        };
        timer.schedule(timerTask, 0, 100);
    }

    //停止读取
    private void stopReadRssi() {
        if (timerTask != null) {
            timerTask.cancel();
        }
        if (timer != null) {
            timer.cancel();
        }
        timerTask = null;
        timer = null;
    }


    //进行资源回收
    public void close() {
        stopReadRssi();
        if (gatt != null)
            gatt.disconnect();


    }
}
