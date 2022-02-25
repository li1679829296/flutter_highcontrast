
import 'dart:async';
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter/widgets.dart';

abstract class HighContrast {
  success(bool isHigh);
}

class FlutterHighcontrast implements HighContrast{
  static const MethodChannel _channel = MethodChannel('flutter_highcontrast');

  static Future<String?> get platformVersion async {
    final String? version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  bool is_highContrastText = false;

  @override
  success(bool isHigh) {
    // TODO: implement success
    is_highContrastText = isHigh;
  }

  bool isHighContrastText(BuildContext context) {
    if (Platform.isIOS) {
      //ios相关代码
      if (MediaQuery.of(context).highContrast) {
        return true;
      } else {
        return false;
      }
    } else if (Platform.isAndroid) {
      //android相关代码
      return is_highContrastText;
    }
    return false;
  }

  factory FlutterHighcontrast() => _highcontrast();

  static FlutterHighcontrast? _instance;

  FlutterHighcontrast._() {
    getHigh(this);
  }

  static FlutterHighcontrast _highcontrast() {
    _instance ??= FlutterHighcontrast._();
    return _instance!;
  }

  void refreshBool() {
    if (_instance != null) getHigh(_instance!);
  }

  bool isDarkMode(BuildContext context) {
    return Theme.of(context).brightness == Brightness.dark;
  }

  static void getHigh(HighContrast highContrast) {
    const EventChannel highContrastText = EventChannel('com.example.flutter_high_contrast.android/high_contrast_text');
    highContrastText.receiveBroadcastStream().listen((dynamic payResult) {
      if (payResult != null && payResult != "") {
        highContrast.success(payResult);
      }
    });
  }
}
