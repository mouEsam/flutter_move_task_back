import 'dart:async';
import 'dart:io';

import 'package:flutter/services.dart';

class FlutterMoveTaskBack {
  static const MethodChannel _channel =
      const MethodChannel('flutter_move_task_back');

  static Future<void> moveTaskToBack({bool nonRoot = true}) async {
    if (Platform.isAndroid) {
      return await _channel
          .invokeMethod('moveTaskToBack', {"nonRoot": nonRoot});
    } else {
      print("this platform not soupport moveTaskToBack.");
      return Future.value();
    }
  }
}
