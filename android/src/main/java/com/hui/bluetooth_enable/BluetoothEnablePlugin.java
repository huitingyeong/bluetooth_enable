package com.hui.bluetooth_enable;

import android.app.Activity;
import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;
import io.flutter.plugin.common.PluginRegistry.RequestPermissionsResultListener;
import io.flutter.plugin.common.PluginRegistry.ActivityResultListener;
import io.flutter.plugin.common.PluginRegistry;

/** FlutterBluePlugin */
public class BluetoothEnablePlugin implements MethodCallHandler, ActivityResultListener, PluginRegistry.RequestPermissionsResultListener {
    private static final String TAG = "BluetoothEnablePlugin";
    private final Registrar registrar;
    private final Activity activity;
    private final MethodChannel channel;
    private final BluetoothManager mBluetoothManager;
    private BluetoothAdapter mBluetoothAdapter;
    private Result pendingResult;

    private static final int REQUEST_ENABLE_BLUETOOTH = 1;
    private static final int REQUEST_CODE_SCAN_ACTIVITY = 2777;

    /** Plugin registration. */
    public static void registerWith(Registrar registrar) {
        final BluetoothEnablePlugin instance = new BluetoothEnablePlugin(registrar);
        registrar.addActivityResultListener(instance);
        registrar.addRequestPermissionsResultListener(instance);
    }

    BluetoothEnablePlugin(Registrar r){
        this.registrar = r;
        this.activity = r.activity();
        this.channel = new MethodChannel(registrar.messenger(), "bluetooth_enable");
        this.mBluetoothManager = (BluetoothManager) r.activity().getSystemService(Context.BLUETOOTH_SERVICE);
        this.mBluetoothAdapter = mBluetoothManager.getAdapter();
        channel.setMethodCallHandler(this);
    }

    @Override
    public void onMethodCall(MethodCall call, Result result) {
        if(mBluetoothAdapter == null && !"isAvailable".equals(call.method)) {
            result.error("bluetooth_unavailable", "the device does not have bluetooth", null);
            return;
        }

        switch (call.method) {
            case "enableBluetooth":
            {  
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                activity.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BLUETOOTH);
                pendingResult = result;
                break;
            }
            default:
            {
                result.notImplemented();
                break;
            }
        }
    }

    @Override
    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ENABLE_BLUETOOTH){        
          if (resultCode == Activity.RESULT_OK) {
              Log.d(TAG, "User enabled Bluetooth");
              pendingResult.success("true");
          }
          else{
              Log.d(TAG, "User did NOT enabled Bluetooth");
              pendingResult.success("false");
          }
        }
        return false;
    }

    @Override
    public boolean onRequestPermissionsResult(
            int requestCode, String[] permissions, int[] grantResults) {
        System.out.println("TWO");

        return false;
    }
}