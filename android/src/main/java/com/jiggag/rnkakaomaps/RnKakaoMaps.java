
package com.jiggag.rnkakaomaps;

import android.app.Activity;
import android.content.Intent;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

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
  public void showKakaoMap() {
      activity = getCurrentActivity();
      Intent i = new Intent();
      i.setClass(activity, mapViewClass);
      activity.startActivity(i);
      activity.finish();

  }
}
