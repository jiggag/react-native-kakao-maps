package com.jiggag.rnkakaomaps;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class MapViewActivity extends FragmentActivity implements MapView.OpenAPIKeyAuthenticationResultListener, MapView.MapViewEventListener {

  private static final String LOG_TAG = "MapViewDemoActivity";

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
  public boolean onCreateOptionsMenu(Menu menu) {
    super.onCreateOptionsMenu(menu);
    menu.add(0, MENU_MAP_TYPE, Menu.NONE, "MapType");
    menu.add(0, MENU_MAP_MOVE, Menu.NONE, "Move");

    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    final int itemId = item.getItemId();

    switch (itemId) {
      case MENU_MAP_TYPE: {

        //String hdMapTile = mMapView.isHDMapTileEnabled()? "HD Map Tile Off" : "HD Map Tile On";

        String hdMapTile;

        if (mMapView.getMapTileMode() == MapView.MapTileMode.HD2X) {
          hdMapTile = "Set to Standard Mode";
        } else if (mMapView.getMapTileMode() == MapView.MapTileMode.HD) {
          hdMapTile = "Set to HD 2X Mode";
        } else {
          hdMapTile = "Set to HD Mode";
        }

        String[] mapTypeMenuItems = { "Standard", "Satellite", "Hybrid", hdMapTile, "Clear Map Tile Cache"};

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("MapType");
        dialog.setItems(mapTypeMenuItems, new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            controlMapTile(which);
          }
        });
        dialog.show();


        return true;
      }

      case MENU_MAP_MOVE: {
        String rotateMapMenu = mMapView.getMapRotationAngle() == 0.0f? "Rotate Map 60" : "Unrotate Map";
        String[] mapMoveMenuItems = { "Move to", "Zoom to", "Move and Zoom to", "Zoom In", "Zoom Out", rotateMapMenu};

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Move");
        dialog.setItems(mapMoveMenuItems, new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            controlMapMove(which);
          }

        });
        dialog.show();

        return true;
      }
    }


    return super.onOptionsItemSelected(item);
  }

  private void controlMapMove(int which) {
    switch (which) {
      case 0: // Move to
      {
        mMapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.53737528, 127.00557633), true);
      }
      break;
      case 1: // Zoom to
      {
        mMapView.setZoomLevel(7, true);
      }
      break;
      case 2: // Move and Zoom to
      {
        mMapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(33.41, 126.52), 9, true);
      }
      break;
      case 3: // Zoom In
      {
        mMapView.zoomIn(true);
      }
      break;
      case 4: // Zoom Out
      {
        mMapView.zoomOut(true);
      }
      break;
      case 5: // Rotate Map 60, Unrotate Map
      {
        if (mMapView.getMapRotationAngle() == 0.0f) {
          mMapView.setMapRotationAngle(60.0f, true);
        } else {
          mMapView.setMapRotationAngle(0.0f, true);
        }
      }
      break;
    }
  }

  /**
   * 지도 타일 컨트롤.
   */
  private void controlMapTile(int which) {
    switch (which) {
      case 0: // Standard
      {
        mMapView.setMapType(MapView.MapType.Standard);
      }
      break;
      case 1: // Satellite
      {
        mMapView.setMapType(MapView.MapType.Satellite);
      }
      break;
      case 2: // Hybrid
      {
        mMapView.setMapType(MapView.MapType.Hybrid);
      }
      break;
      case 3: // HD Map Tile On/Off
      {
        if (mMapView.getMapTileMode() == MapView.MapTileMode.HD2X) {
          //Set to Standard Mode
          mMapView.setMapTileMode(MapView.MapTileMode.Standard);
        } else if (mMapView.getMapTileMode() == MapView.MapTileMode.HD) {
          //Set to HD 2X Mode
          mMapView.setMapTileMode(MapView.MapTileMode.HD2X);
        } else {
          //Set to HD Mode
          mMapView.setMapTileMode(MapView.MapTileMode.HD);
        }
      }
      break;
      case 4: // Clear Map Tile Cache
      {
        MapView.clearMapTilePersistentCache();
      }
      break;
    }
  }

  //	/////////////////////////////////////////////////////////////////////////////////////////////////
  // net.daum.mf.map.api.MapView.OpenAPIKeyAuthenticationResultListener

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
    MapPoint.GeoCoordinate mapPointGeo = mapCenterPoint.getMapPointGeoCoord();
    Log.i(LOG_TAG, String.format("MapView onMapViewCenterPointMoved (%f,%f)", mapPointGeo.latitude, mapPointGeo.longitude));
  }

  @Override
  public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {

    MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();

    AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
    alertDialog.setTitle("DaumMapLibrarySample");
    alertDialog.setMessage(String.format("Double-Tap on (%f,%f)", mapPointGeo.latitude, mapPointGeo.longitude));
    alertDialog.setPositiveButton("OK", null);
    alertDialog.show();
  }

  @Override
  public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {

    MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();

    AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
    alertDialog.setTitle("DaumMapLibrarySample");
    alertDialog.setMessage(String.format("Long-Press on (%f,%f)", mapPointGeo.latitude, mapPointGeo.longitude));
    alertDialog.setPositiveButton("OK", null);
    alertDialog.show();
  }

  @Override
  public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {
    MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();
    Log.i(LOG_TAG, String.format("MapView onMapViewSingleTapped (%f,%f)", mapPointGeo.latitude, mapPointGeo.longitude));
  }

  @Override
  public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {
    MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();
    Log.i(LOG_TAG, String.format("MapView onMapViewDragStarted (%f,%f)", mapPointGeo.latitude, mapPointGeo.longitude));
  }

  @Override
  public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {
    MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();
    Log.i(LOG_TAG, String.format("MapView onMapViewDragEnded (%f,%f)", mapPointGeo.latitude, mapPointGeo.longitude));
  }

  @Override
  public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {
    MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();
    Log.i(LOG_TAG, String.format("MapView onMapViewMoveFinished (%f,%f)", mapPointGeo.latitude, mapPointGeo.longitude));
  }

  @Override
  public void onMapViewZoomLevelChanged(MapView mapView, int zoomLevel) {
    Log.i(LOG_TAG, String.format("MapView onMapViewZoomLevelChanged (%d)", zoomLevel));
  }

}
