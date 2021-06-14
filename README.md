## Introduction

BluetoothEnable is a Bluetooth plugin for [Flutter](https://www.flutter.io), to programtically request turning on Bluetooth within applications. 

This plugin was created with the intention to allow developers to customise their own application workflow on when permissions are requested.

This plugin is supported only for Android.

## Usage

### Enable Bluetooth

```dart
import 'package:bluetooth_enable/bluetooth_enable.dart';

// Request to turn on Bluetooth within an app
BluetoothEnable.enableBluetooth.then((result) {
  if (result == "true") {
    // Bluetooth has been enabled
  }
  else if (result == "false") {
    // Bluetooth has not been enabled
  }
});
```

### Enable Bluetooth with Custom Alert

This method calls an alert from Dart level. Depending on the user's selection, Bluetooth will either be manually turned on without an additional dialog request or nothing happens.

|Parameter       |Description                          |
|----------------|-------------------------------|
|context				 |The material context of your current content            |
|dialogTitle    		 |The title of the dialog is displayed in a large font at the top|
|displayDialogContent 	|Enable or disable showing dialog content
|dialogContent 			|The content of the dialog that is displayed in the center|
|cancelBtnText 			|The cancel button text of the dialog (left button)|
|acceptBtnText 			|The accept button text of the dialog (right button)|
|dialogRadius 			|The border radius of dialog|
|barrierDismissible 	|Enable or Disable whether dialog is dismissible on external click|


```dart
import  'package:bluetooth_enable/bluetooth_enable.dart';

Future<void> customEnableBT(BuildContext context) async {
  String dialogTitle = "Hey! Please give me permission to use Bluetooth!";
  bool displayDialogContent = true;
  String dialogContent = "This app requires Bluetooth to connect to device.";
  //or
  // bool displayDialogContent = false;
  // String dialogContent = "";
  String cancelBtnText = "Nope";
  String acceptBtnText = "Sure";
  double dialogRadius = 10.0;
  bool barrierDismissible = true; //

  BluetoothEnable.customBluetoothRequest(context, dialogTitle, displayDialogContent, dialogContent, cancelBtnText, acceptBtnText, dialogRadius, barrierDismissible).then((result) {
    if (result == "true") {
      //Bluetooth has been enabled
    }
  });
}
```
