#import "MapView.h"
#import <DaumMap/MTMapView.h>

@interface MapView () {
  MTMapView* _mapView;
}

@end

@implementation MapView

- (instancetype)init
{
  self = [super init];
  if (self) {
    _mapView = [[MTMapView alloc] init];
    _mapView.baseMapType = MTMapTypeStandard;

    double lat = [_centerPoint[@"lat"] doubleValue];
    double lng = [_centerPoint[@"lng"] doubleValue];

    [_mapView fitMapViewAreaToShowAllPOIItems];
    [_mapView setMapCenterPoint:[MTMapPoint mapPointWithGeoCoord:MTMapPointGeoMake(lat, lng)] animated:YES];
    [_mapView addPOIItems: createMarkerList(_markerList)];

    [self addSubview: _mapView];
  }

  return self;
}

- (void)setMarkerList:(NSArray*)markerList {
  _markerList = markerList;
}

- (void)setCenterPoint:(NSDictionary*)centerPoint {
  _centerPoint = centerPoint;
}

NSArray *createMarkerList(NSArray* markerList) {
  NSMutableArray* pointList = [[NSMutableArray alloc] init];

  for (NSDictionary* point in markerList) {
    MTMapPOIItem* item = [MTMapPOIItem poiItem];
    item.itemName = point[@"markerName"];
    item.mapPoint = [MTMapPoint mapPointWithGeoCoord:MTMapPointGeoMake([point[@"lat"] doubleValue], [point[@"lng"] doubleValue])];
    item.markerType = MTMapPOIItemMarkerTypeBluePin;
    item.showAnimationType = MTMapPOIItemShowAnimationTypeSpringFromGround;

    [pointList addObject:item];
  }

  return pointList;
}


@end
