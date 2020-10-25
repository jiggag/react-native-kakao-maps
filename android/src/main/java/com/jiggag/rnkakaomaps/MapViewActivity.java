package com.jiggag.rnkakaomaps;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.jiggag.rnkakaomaps.RnKakaoMaps;
import net.daum.mf.map.api.MapLayout;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapView;

import java.util.ArrayList;
import java.util.HashMap;

public class MapViewActivity extends FragmentActivity implements MapView.OpenAPIKeyAuthenticationResultListener, MapView.MapViewEventListener {

  private static final String LOG_TAG = "MapViewActivity";
  private static final String MARKER_NAME = "Default Marker";
  private static final Double INIT_LAT = 37.537229;
  private static final Double INIT_LNG = 127.005515;
  private static final String PARAM_MARKER_NAME = "markerName";
  private static final String PARAM_LAT = "lat";
  private static final String PARAM_LNG = "lng";

  private MapView mMapView;
  private MapPOIItem mDefaultMarker;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.map_view);

    MapLayout mapLayout = new MapLayout(this);
    mMapView = mapLayout.getMapView();


    mMapView.setDaumMapApiKey(this.getString(R.string.kakao_app_key));
    mMapView.setOpenAPIKeyAuthenticationResultListener(this);
    mMapView.setMapViewEventListener(this);
    mMapView.setMapType(MapView.MapType.Standard);

    // TODO Delete default marker
    createDefaultMarker(mMapView);

    createMarker((ArrayList<HashMap<String, Object>>) getIntent().getSerializableExtra("markerList"));

    ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
    mapViewContainer.addView(mapLayout);
  }

  private void createDefaultMarker(MapView mapView) {
    mDefaultMarker = new MapPOIItem();
    mDefaultMarker.setItemName(MARKER_NAME);
    mDefaultMarker.setTag(0);
    mDefaultMarker.setMapPoint(MapPoint.mapPointWithGeoCoord(INIT_LAT, INIT_LNG));
    mDefaultMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
    mDefaultMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

    mapView.addPOIItem(mDefaultMarker);
    mapView.selectPOIItem(mDefaultMarker, true);
    mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(INIT_LAT, INIT_LNG), true);
  }

  private void createMarker(ArrayList<HashMap<String, Object>> markerList) {
    if ((markerList != null ? markerList.size() : 0) > 0) {
      for (int i = 0;i < markerList.size(); i++) {
        String markerName = (String) markerList.get(i).get(PARAM_MARKER_NAME);
        Double lat = (Double) markerList.get(i).get(PARAM_LAT);
        Double lng = (Double) markerList.get(i).get(PARAM_LNG);
        addMarker(mMapView, markerName, lat, lng, i);
      }
    }
  }

  private void addMarker(MapView mapView, String markName, Double lat, Double lng, int tag) {
    mDefaultMarker = new MapPOIItem();
    mDefaultMarker.setItemName(markName);
    mDefaultMarker.setTag(tag);
    mDefaultMarker.setMapPoint(MapPoint.mapPointWithGeoCoord(lat,lng));
    mDefaultMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
    mDefaultMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

    mapView.addPOIItem(mDefaultMarker);
  }

  @Override
  public void onBackPressed() {
    Log.i(LOG_TAG, "Back button pressed");
    RnKakaoMaps.onBackPressed();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onDaumMapOpenAPIKeyAuthenticationResult(MapView mapView, int resultCode, String resultMessage) {
    Log.i(LOG_TAG,	String.format("Open API Key Authentication Result : code=%d, message=%s", resultCode, resultMessage));
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////
  // net.daum.mf.map.api.MapView.MapViewEventListener

  public void onMapViewInitialized(MapView mapView) {
    Log.i(LOG_TAG, "MapView had loaded. Now, MapView APIs could be called safely");
    //mMapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
    mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(37.537229,127.005515), 2, true);
  }

  @Override
  public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapCenterPoint) {
  }

  @Override
  public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {
  }

  @Override
  public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {
  }

  @Override
  public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {
  }

  @Override
  public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {
  }

  @Override
  public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {
  }

  @Override
  public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {
  }

  @Override
  public void onMapViewZoomLevelChanged(MapView mapView, int zoomLevel) {
  }
}
