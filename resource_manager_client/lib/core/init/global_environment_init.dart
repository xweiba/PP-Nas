import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:get/get_utils/src/platform/platform.dart';

class GlobalEnvironmentInit {
  static Future<void> init() async {
    await android();
  }

  static Future<void> defaultInit() async {
  }

  static Future<void> android() async {
    if (GetPlatform.isAndroid) {
      // Android 沉浸式状态栏
      SystemChrome.setSystemUIOverlayStyle(
        const SystemUiOverlayStyle(
          statusBarColor: Colors.transparent,
          statusBarIconBrightness: Brightness.dark,
          statusBarBrightness: Brightness.light,
        ),
      );
    }

  }

  static Future<void> ios() async {
  }

  static Future<void> window() async {
  }

  static Future<void> linux() async {
  }

  static Future<void> web() async {
  }
}