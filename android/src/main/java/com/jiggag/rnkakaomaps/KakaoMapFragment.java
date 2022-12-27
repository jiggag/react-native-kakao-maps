package com.jiggag.rnkakaomaps;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.view.MenuItem;

import net.daum.mf.map.api.MapLayout;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.events.RCTEventEmitter;

public class KakaoMapFragment extends Fragment implements MapView.OpenAPIKeyAuthenticationResultListener, MapView.MapViewEventListener {
    ReactContext reactContext;

    private MapView mMapView;
    private MapPOIItem mDefaultMarker;
    private Bitmap markerImage = null;
    MapLayout mapLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapLayout = new MapLayout(getActivity());
    }

    private void createMapView() {
        mMapView.removeAllPOIItems();

        Double lat = getArguments().getDouble(Constants.PARAM_LAT);
        Double lng = getArguments().getDouble(Constants.PARAM_LNG);
        mMapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(lat, lng), true);

        ArrayList<HashMap<String, Object>> markerList = (ArrayList<HashMap<String, Object>>) getArguments().get(Constants.PARAM_MARKER_LIST);
        String markerImageUrl = getArguments().getString(Constants.PARAM_MARKER_IMAGE_URL);
        String markerImageName = getArguments().getString(Constants.PARAM_MARKER_NAME);
        int markerImageResourceId = markerImageName != null ? this.getResources().getIdentifier(markerImageName, "drawable", getActivity().getPackageName()) : 0;

        new Thread(() -> {
            getBitmapFromUrl(markerImageUrl);
            createMarker(markerList, markerImage, markerImageResourceId);
        }).start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        super.onCreateView(inflater, parent, savedInstanceState);

        View rootView = inflater.inflate(R.layout.kakao_map_view, parent, false);

        mMapView = mapLayout.getMapView();
        mMapView.setDaumMapApiKey(this.getString(R.string.kakao_app_key));
        mMapView.setOpenAPIKeyAuthenticationResultListener(this);
        mMapView.setMapViewEventListener(this);
        mMapView.setMapType(MapView.MapType.Standard);

        createMapView();

        ViewGroup mapViewContainer = rootView.findViewById(R.id.kakao_map_view);
        mapViewContainer.addView(mapLayout);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        createMapView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    //	/////////////////////////////////////////////////////////////////////////////////////////////////
    // net.daum.mf.map.api.MapView.OpenAPIKeyAuthenticationResultListener

    @Override
    public void onDaumMapOpenAPIKeyAuthenticationResult(MapView mapView, int resultCode, String resultMessage) {
        Log.i(Constants.LOG_TAG, String.format("Open API Key Authentication Result : code=%d, message=%s", resultCode, resultMessage));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    // net.daum.mf.map.api.MapView.MapViewEventListener

    public void onMapViewInitialized(MapView mapView) {
        Log.i(Constants.LOG_TAG, "MapView had loaded. Now, MapView APIs could be called safely");
    }

    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapCenterPoint) {
        MapPoint.GeoCoordinate mapPointGeo = mapCenterPoint.getMapPointGeoCoord();
        Log.i(Constants.LOG_TAG, String.format("MapView onMapViewCenterPointMoved (%f,%f)", mapPointGeo.latitude, mapPointGeo.longitude));
    }

    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {
        MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();
        Log.i(Constants.LOG_TAG, String.format("MapView onMapViewDoubleTapped (%f,%f)", mapPointGeo.latitude, mapPointGeo.longitude));
    }

    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {
        MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();
        Log.i(Constants.LOG_TAG, String.format("MapView onMapViewLongPressed (%f,%f)", mapPointGeo.latitude, mapPointGeo.longitude));
    }

    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {
        MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();
        Log.i(Constants.LOG_TAG, String.format("MapView onMapViewSingleTapped (%f,%f)", mapPointGeo.latitude, mapPointGeo.longitude));
    }

    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {
        MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();
        Log.i(Constants.LOG_TAG, String.format("MapView onMapViewDragStarted (%f,%f)", mapPointGeo.latitude, mapPointGeo.longitude));
    }

    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {
        MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();
        Log.i(Constants.LOG_TAG, String.format("MapView onMapViewDragEnded (%f,%f)", mapPointGeo.latitude, mapPointGeo.longitude));
    }

    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {
        MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();
        WritableMap event = Arguments.createMap();
        event.putDouble("lat", mapPointGeo.latitude);
        event.putDouble("lng", mapPointGeo.longitude);
        event.putDouble("zoomLevel", mapView.getZoomLevel());
        onReceiveNativeEvent(event);
        Log.i(Constants.LOG_TAG, String.format("MapView onMapViewMoveFinished (%f,%f)", mapPointGeo.latitude, mapPointGeo.longitude));
    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int zoomLevel) {
        Log.i(Constants.LOG_TAG, String.format("MapView onMapViewZoomLevelChanged (%d)", zoomLevel));
    }

    private void onReceiveNativeEvent(WritableMap event) {
        reactContext
                .getJSModule(RCTEventEmitter.class)
                .receiveEvent(getId(), "topChange", event);
    }

    private Map getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.builder().put(
                "topChange",
                MapBuilder.of(
                        "phasedRegistrationNames",
                        MapBuilder.of("bubbled", "onChange")
                )
        ).build();
    }

    private void getBitmapFromUrl(String imageUrl) {
        if (imageUrl == null) {
            return;
        }

        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();

            markerImage = BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            Log.e("getBitmapFromUrl", e.getMessage());
        }
    }

    private void createMarker(ArrayList<HashMap<String, Object>> markerList, Bitmap markerImage, int markerResourceId) {
        if ((markerList != null ? markerList.size() : 0) > 0) {
            for (int i = 0; i < markerList.size(); i++) {
                String markerName = (String) markerList.get(i).get(Constants.PARAM_MARKER_NAME);
                Double lat = (Double) markerList.get(i).get(Constants.PARAM_LAT);
                Double lng = (Double) markerList.get(i).get(Constants.PARAM_LNG);
                addMarker(mMapView, markerName, lat, lng, i, markerImage, markerResourceId);
            }
        }
    }

    private void addMarker(MapView mapView, String markName, Double lat, Double lng, int tag, Bitmap markerImage, int markerResourceId) {
        mDefaultMarker = new MapPOIItem();
        mDefaultMarker.setItemName(markName);
        mDefaultMarker.setTag(tag);
        mDefaultMarker.setMapPoint(MapPoint.mapPointWithGeoCoord(lat, lng));
        mDefaultMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        mDefaultMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        if (markerImage != null) {
            mDefaultMarker.setMarkerType(MapPOIItem.MarkerType.CustomImage);
            mDefaultMarker.setCustomImageBitmap(markerImage);
        }

        if (markerResourceId > 0) {
            mDefaultMarker.setMarkerType(MapPOIItem.MarkerType.CustomImage);
            mDefaultMarker.setCustomImageResourceId(markerResourceId);
        }

        mapView.addPOIItem(mDefaultMarker);
    }
}
