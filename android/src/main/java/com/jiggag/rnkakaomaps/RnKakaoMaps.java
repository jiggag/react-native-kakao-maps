package com.jiggag.rnkakaomaps;

import android.app.Activity;
import android.content.Intent;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableArray;

public class RnKakaoMaps extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;
  private Activity activity;
  private static Class mapViewClass = MapViewActivity.class;

  public RnKakaoMaps(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RnKakaoMaps";
  }

  @ReactMethod
  public void showKakaoMap(ReadableArray markerList, @Nullable ReadableMap centerPoint) {
      activity = getCurrentActivity();
      Intent intent = new Intent();
      intent.setClass(activity, mapViewClass);
      intent.putExtra("markerList", markerList.toArrayList());
      intent.putExtra("centerPoint", centerPoint != null ? centerPoint.toHashMap() : new HashMap<String, Object>());
      activity.startActivity(intent);
      activity.finish();
  }
}
