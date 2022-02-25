package com.high.contrast.flutter_highcontrast;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.accessibility.AccessibilityManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Method;

import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * FlutterHighcontrastPlugin
 */
public class FlutterHighcontrastPlugin implements FlutterPlugin, MethodCallHandler {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private MethodChannel channel;

    private EventChannel highChannel;


    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "flutter_highcontrast");
        channel.setMethodCallHandler(this);

        ((Application)flutterPluginBinding.getApplicationContext()).registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
                isHighContrastTextEnabled(flutterPluginBinding.getApplicationContext());
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {

            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {
                isHighContrastTextEnabled(flutterPluginBinding.getApplicationContext());
            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {

            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
                if (activity == flutterPluginBinding.getApplicationContext()){
                    ((Application)flutterPluginBinding.getApplicationContext()).unregisterActivityLifecycleCallbacks(this);
                }
            }
        });
        highChannel = new EventChannel(flutterPluginBinding.getBinaryMessenger(), HIGH_CONTRAST_TEXT);
        highChannel.setStreamHandler(
                new EventChannel.StreamHandler() {
                    @Override
                    public void onListen(Object args, final EventChannel.EventSink events) {
                        eventHighContrastText = events;
                        isHighContrastTextEnabled(flutterPluginBinding.getApplicationContext());
                    }

                    @Override
                    public void onCancel(Object args) {
                    }
                }
        );
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        if (call.method.equals("getPlatformVersion")) {
            result.success("Android " + android.os.Build.VERSION.RELEASE);
        } else {
            result.notImplemented();
        }
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        channel.setMethodCallHandler(null);
    }


    private static final String HIGH_CONTRAST_TEXT = "com.example.flutter_high_contrast.android/high_contrast_text";
    private EventChannel.EventSink eventHighContrastText;

    public  void isHighContrastTextEnabled(Context context) {
        if (context != null) {
            AccessibilityManager am = (AccessibilityManager) context.getSystemService(Context.ACCESSIBILITY_SERVICE);
            Method m = null;

            if (am != null) {
                try {
                    m = am.getClass().getMethod("isHighTextContrastEnabled");
                } catch (NoSuchMethodException e) {
                }
            }

            Object result;
            if (m != null) {
                try {
                    result = m.invoke(am);
                    if (result instanceof Boolean) {
                        if (eventHighContrastText != null){
                            eventHighContrastText.success((Boolean) result);
                        }
                    }
                } catch (Exception e) {
                }
            }
        }

    }

}
