import 'dart:async';

import 'package:flutter/services.dart';

class BluetoothEnable {
  static const MethodChannel _channel =
      const MethodChannel('bluetooth_enable');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  Future<String> enableBluetooth() async {
    print("enableBluetooth");
    String enableState = await _channel.invokeMethod('enableBluetooth').then<String>((d) => d);
    return enableState;
  }  
}
