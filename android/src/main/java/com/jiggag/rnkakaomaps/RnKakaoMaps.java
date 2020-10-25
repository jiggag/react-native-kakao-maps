
package com.jiggag.rnkakaomaps;

import android.app.Activity;
import android.content.Intent;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableArray;

public class RnKakaoMaps extends ReactContextBaseJavaModule {
  private static Activity activity;
  private static Intent intent;
  private static Intent originIntent;
  private static ReactApplicationContext reactContext;

  private static final String LOG_TAG = "RnKakaoMaps";


  public RnKakaoMaps(ReactApplicationContext context) {
    super(context);
    reactContext = context;
  }

  @Override
  public String getName() {
    return "RnKakaoMaps";
  }


  @ReactMethod
  public void showKakaoMap(ReadableArray markerList) {
    intent = new Intent();
    originIntent = new Intent();
    intent.setClass(reactContext, MapViewActivity.class);
    intent.putExtra("markerList", markerList.toArrayList());
    if (intent.resolveActivity(reactContext.getPackageManager()) != null) {
      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      reactContext.startActivity(intent);
    }
  }

  public static void onBackPressed() {
    reactContext.startActivity(originIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
  }
}
