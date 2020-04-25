import 'package:flutter/material.dart';
import 'package:bluetooth_enable/bluetooth_enable.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {

  @override
  void initState() {
    super.initState();
  }

  Future<void> test() async{
    BluetoothEnable.enableBluetooth.then((value){
      print(value);
    });    
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text(
            'Turn on Bluetooth',
          ),
          centerTitle: true,
        ),
        body: Center(
          child: Column(
            mainAxisSize: MainAxisSize.min,
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Text("Press the button to request turning on Bluetooth"),
              SizedBox(height: 20.0),
              RaisedButton(
                onPressed: (() {
                  test();
                }),
                child: Text('Request to turn on Bluetooth'),
              ),
            ],
          ),
        ),
      ),
    );
  }
}