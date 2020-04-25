## Introduction

BluetoothEnable is a bluetooth plugin for [Flutter](http://www.flutter.io), to programtically request turning on Bluetooth within applications.

This plugin is for Android only.

## Usage
```dart
// Request to turn on Bluetooth within an app
BluetoothEnable.enableBluetooth.then((value){
  //Do something with value
  //On success, returns "true". On error, returns "false"
    print(value);
  });  
```