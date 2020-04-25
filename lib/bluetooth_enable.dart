import 'dart:async';

import 'package:flutter/services.dart';

class BluetoothEnable {
  static const MethodChannel _channel =
      const MethodChannel('bluetooth_enable');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<String> get enableBluetooth async {
    final String bluetoothState = await _channel.invokeMethod('enableBluetooth');
    return bluetoothState;
  } 
}
