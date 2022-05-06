package com.jiggag.rnkakaomaps;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

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

public class MapViewActivity extends FragmentActivity implements MapView.OpenAPIKeyAuthenticationResultListener, MapView.MapViewEventListener {
  private MapView mMapView;
  private MapPOIItem mDefaultMarker;
  private Bitmap markerImage = null;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.kakao_map_view);

    MapLayout mapLayout = new MapLayout(this);
    mMapView = mapLayout.getMapView();

    mMapView.setDaumMapApiKey(this.getString(R.string.kakao_app_key));
    mMapView.setOpenAPIKeyAuthenticationResultListener(this);
    mMapView.setMapViewEventListener(this);
    mMapView.setMapType(MapView.MapType.Standard);

    HashMap<String, Object> centerPoint = (HashMap<String, Object>) getIntent().getSerializableExtra("centerPoint");
    Double lat = (Double) centerPoint.get(Constants.PARAM_LAT);
    Double lng = (Double) centerPoint.get(Constants.PARAM_LNG);
    mMapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(lat, lng), true);

    ArrayList<HashMap<String, Object>> markerList = (ArrayList<HashMap<String, Object>>) getIntent().getSerializableExtra("markerList");
    String markerImageUrl = (String) getIntent().getSerializableExtra("markerImageUrl");

    String markerImageName = (String) getIntent().getSerializableExtra("markerImageName");
    int markerImageResourceId = markerImageName != null ? this.getResources().getIdentifier(markerImageName, "drawable", this.getPackageName()) : 0;

    new Thread(() -> {
      getBitmapFromUrl(markerImageUrl);
      createMarker(markerList, markerImage, markerImageResourceId);
    }).start();

    ViewGroup mapViewContainer = findViewById(R.id.kakao_map_view);
    mapViewContainer.addView(mapLayout);
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
      for (int i = 0;i < markerList.size(); i++) {
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
    mDefaultMarker.setMapPoint(MapPoint.mapPointWithGeoCoord(lat,lng));
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

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    return super.onOptionsItemSelected(item);
  }

  //	/////////////////////////////////////////////////////////////////////////////////////////////////
  // net.daum.mf.map.api.MapView.OpenAPIKeyAuthenticationResultListener

  @Override
  public void onDaumMapOpenAPIKeyAuthenticationResult(MapView mapView, int resultCode, String resultMessage) {
    Log.i(Constants.LOG_TAG,	String.format("Open API Key Authentication Result : code=%d, message=%s", resultCode, resultMessage));
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
    Log.i(Constants.LOG_TAG, String.format("MapView onMapViewMoveFinished (%f,%f)", mapPointGeo.latitude, mapPointGeo.longitude));
  }

  @Override
  public void onMapViewZoomLevelChanged(MapView mapView, int zoomLevel) {
    Log.i(Constants.LOG_TAG, String.format("MapView onMapViewZoomLevelChanged (%d)", zoomLevel));
  }

  @Override
  public void onBackPressed() {
    finish();
  }
}
