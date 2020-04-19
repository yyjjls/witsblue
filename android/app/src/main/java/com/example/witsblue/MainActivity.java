package com.example.witsblue;
import android.content.Intent;
import android.os.Bundle;


import io.flutter.app.FlutterActivity;
public class MainActivity extends FlutterActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BlueManager.registerWith(this);

      /*  Intent intent1= new Intent(getApplication(), BleBackstageScanService.class);
        getApplication().startService(intent1);*/
    }
}
