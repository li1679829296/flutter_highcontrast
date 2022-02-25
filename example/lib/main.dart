import 'package:flutter/material.dart';
import 'dart:async';
import 'package:flutter_highcontrast/flutter_highcontrast.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _highcontrast = '';

  @override
  void initState() {
    super.initState();
    init();
  }

  Future<void> init() async {
    FlutterHighcontrast();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Column(
              mainAxisAlignment:MainAxisAlignment.center,
              children: [
          Center(
          child: Text('是否开启高对比度: ' + _highcontrast),
        ),
        ElevatedButton(onPressed: () async {
          setState(() {
            _highcontrast =
                FlutterHighcontrast().isHighContrastText(context).toString();
          });
        }, child: const Text("Get"))
        ],
      ),
    ),)
    ,
    );
  }
}
