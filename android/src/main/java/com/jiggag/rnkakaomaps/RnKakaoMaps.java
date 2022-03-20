package com.jiggag.rnkakaomaps;

import android.app.Activity;
import android.content.Intent;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

import java.util.ArrayList;
import java.util.HashMap;

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

    @Deprecated
    @ReactMethod
    public void showKakaoMap(ReadableArray markerList) {
        HashMap<String, Object> params = new HashMap();
        params.put("markerList", markerList.toArrayList());
        startKakaoMap(params);
    }

    @ReactMethod
    public void show(ReadableMap map) throws Exception {
        HashMap<String, Object> params = map.toHashMap();
        if (params.isEmpty() || params.get("markerList") == null) {
            throw new Exception();
        }
        startKakaoMap(params);
    }

    private void startKakaoMap(HashMap<String, Object> params) {
        String markerImageUrl = (String) params.get("markerImageUrl");
        String markerImageName = (String) params.get("markerImageName");
        ArrayList markerList = (ArrayList) params.get("markerList");
        HashMap<String, Object> centerPoint = new HashMap<>();
        if (params.get("centerPoint") == null) {
            centerPoint.put(Constants.PARAM_LAT, Constants.INIT_LAT);
            centerPoint.put(Constants.PARAM_LNG, Constants.INIT_LNG);
        } else {
            centerPoint.putAll((HashMap<String, Object>) params.get("centerPoint"));
        }

        activity = getCurrentActivity();
        Intent intent = new Intent();
        intent.setClass(activity, mapViewClass);
        intent.putExtra("markerList", markerList);
        intent.putExtra("centerPoint", centerPoint);
        intent.putExtra("markerImageName", markerImageName);
        intent.putExtra("markerImageUrl", markerImageUrl);
        activity.startActivity(intent);
    }
}
