package com.high.contrast.flutter_highcontrast_example;


import io.flutter.embedding.android.FlutterActivity;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;
import android.view.accessibility.AccessibilityManager;
import java.lang.reflect.Method;
import android.os.Bundle;
import io.flutter.embedding.engine.FlutterEngine;
import android.content.Context;
import androidx.annotation.NonNull;
import android.content.Intent;



public class MainActivity extends FlutterActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        GeneratedPluginRegistrant.registerWith(flutterEngine);


    }



}
