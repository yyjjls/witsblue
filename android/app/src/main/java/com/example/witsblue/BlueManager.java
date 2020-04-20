package com.example.witsblue;


import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.witsbluelibrary.induce.Induce;

import io.flutter.app.FlutterActivity;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public class BlueManager implements MethodChannel.MethodCallHandler {
    private static final String CHANNEL = "witsystem.top/blue";
    private FlutterActivity activity;
    static MethodChannel channel;

    private BlueManager(FlutterActivity activity) {
        channel.setMethodCallHandler(this);
        this.activity = activity;
    }

    static void registerWith(FlutterActivity activity) {
        channel = new MethodChannel(activity.getFlutterView(), CHANNEL);
        BlueManager instance = new BlueManager(activity);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
        //开启感应开锁
        if (call.method.equals("openInduceUnlock")) {
            result.success(Induce.instance(activity.getApplication()).openInduceUnlock());
        } else if (call.method.equals("stopInduceUnlock")) {
            result.success(Induce.instance(activity.getApplication()).stopInduceUnlock());
        }
    }


}
