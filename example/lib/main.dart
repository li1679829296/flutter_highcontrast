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
  String _highcontrast = 'false';

  @override
  void initState() {
    super.initState();
    init();
  }

  Future<void> init() async {
    FlutterHighcontrast();

    if(!mounted) return;

    setState(() {
      _highcontrast = FlutterHighcontrast().isHighContrastText(context).toString();
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Text('是否开启高对比度: $_highcontrast\n'),
        ),
      ),
    );
  }
}
