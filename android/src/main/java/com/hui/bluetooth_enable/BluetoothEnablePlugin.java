package com.hui.bluetooth_enable;

import androidx.annotation.NonNull;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

import android.content.Intent;
import android.bluetooth.BluetoothAdapter;

/** BluetoothEnablePlugin */
public class BluetoothEnablePlugin implements FlutterPlugin, MethodCallHandler {

  private static final int REQUEST_ENABLE_BLUETOOTH = 1;
  private final Activity activity;

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    final MethodChannel channel = new MethodChannel(flutterPluginBinding.getFlutterEngine().getDartExecutor(), "bluetooth_enable");
    channel.setMethodCallHandler(new BluetoothEnablePlugin());
  }

  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "bluetooth_enable");
    this.activity = registrar.activity();

    channel.setMethodCallHandler(new BluetoothEnablePlugin());
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if (call.method.equals("getPlatformVersion")) {
      result.success("Android " + android.os.Build.VERSION.RELEASE);
    } 
    else if (call.method.equals("enableBluetooth")){
      System.out.println("enableBluetooth");
      // Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
      Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
      activity.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BLUETOOTH);
      result.success("Enabled");
      break;
    }
    
    else {
      result.notImplemented();
    }
  }

  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    System.out.println("onActivityResult");
    if (requestCode == REQUEST_ENABLE_BLUETOOTH) {
        if (resultCode == Activity.RESULT_OK) {
            Log.d(TAG, "User enabled Bluetooth");
            // if (enableBluetoothCallback != null) {
            //     enableBluetoothCallback.success();
            // }
        } else {
            Log.d(TAG, "User did *NOT* enable Bluetooth");
            // if (enableBluetoothCallback != null) {
            //     enableBluetoothCallback.error("User did not enable Bluetooth");
            // }
        }

        // enableBluetoothCallback = null;
    }
  }


  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
  }
}
