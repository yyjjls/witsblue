package com.example.witsblue;


import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.witsbluelibrary.induce.Induce;
import com.example.witsbluelibrary.sdk.WitsSdk;
import com.example.witsbluelibrary.sdk.WitsSdkInit;

import io.flutter.app.FlutterActivity;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public class BlueManager implements MethodChannel.MethodCallHandler {
    private static final String CHANNEL = "witsystem.top/blue";
    private FlutterActivity activity;
    private static MethodChannel channel;
    private WitsSdk witsSdkInit;

    private BlueManager(FlutterActivity activity) {
        channel.setMethodCallHandler(this);
        this.activity = activity;
        ///初始化SDK 和注册SDK
        witsSdkInit = WitsSdkInit.getInstance().register(activity.getApplication(), "", "");

    }

    static void registerWith(FlutterActivity activity) {
        channel = new MethodChannel(activity.getFlutterView(), CHANNEL);
        BlueManager instance = new BlueManager(activity);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
        if (call.method.equals("witsSdkInit")) {
            //初始化SDK成功返回true
            witsSdkInit = WitsSdkInit.getInstance().register(activity.getApplication(), "", "");
            result.success(witsSdkInit != null);
        } else if (call.method.equals("openInduceUnlock")) {
            //开启感应开锁
            result.success(witsSdkInit.getInduceUnlock().openInduceUnlock());
        } else if (call.method.equals("stopInduceUnlock")) {
            result.success(witsSdkInit.getInduceUnlock().stopInduceUnlock());
        }
    }


}
