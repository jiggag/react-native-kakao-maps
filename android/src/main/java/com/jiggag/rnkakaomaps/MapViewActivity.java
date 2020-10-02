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

public class MapViewActivity extends FragmentActivity implements MapView.OpenAPIKeyAuthenticationResultListener, MapView.MapViewEventListener {

  private static final String LOG_TAG = "MapViewActivity";

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
    createDefaultMarker(mMapView);

    ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
    mapViewContainer.addView(mapLayout);
  }

  private void createDefaultMarker(MapView mapView) {
    mDefaultMarker = new MapPOIItem();
    String name = "Default Marker";
    mDefaultMarker.setItemName(name);
    mDefaultMarker.setTag(0);
    mDefaultMarker.setMapPoint(MapPoint.mapPointWithGeoCoord(37.537229,127.005515));
    mDefaultMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
    mDefaultMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

    mapView.addPOIItem(mDefaultMarker);
    mapView.selectPOIItem(mDefaultMarker, true);
    mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.537229,127.005515), true);
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
