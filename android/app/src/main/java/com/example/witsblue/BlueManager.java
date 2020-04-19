package com.example.witsblue;


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

    @Override
    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
        //开启感应开锁
        if (call.method.equals("openInduceUnlock")) {
            result.success(true);
        }
    }


}
