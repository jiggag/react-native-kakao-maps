#import "MapViewController.h"

@interface MapViewController () {
  MTMapView *_mapView;
}

@end


@implementation MapViewController

NSArray *createMarker(NSArray *markerList, NSString *markerImageUrl, NSString *markerImageName) {
  NSMutableArray *pointList = [[NSMutableArray alloc] init];

  for (NSDictionary *point in markerList) {
    MTMapPOIItem* item = [MTMapPOIItem poiItem];
    item.itemName = point[@"markerName"];
    item.mapPoint = [MTMapPoint mapPointWithGeoCoord:MTMapPointGeoMake([point[@"lat"] doubleValue], [point[@"lng"] doubleValue])];
    item.showAnimationType = MTMapPOIItemShowAnimationTypeSpringFromGround;

    item.markerType = markerImageUrl != nil || markerImageName != nil ? MTMapPOIItemMarkerTypeCustomImage : MTMapPOIItemMarkerTypeBluePin;

    if (markerImageUrl != nil) {
      item.customImage = [[[UIImage alloc] init] initWithData: [NSData dataWithContentsOfURL: [NSURL URLWithString: markerImageUrl]]];
    }
    if (markerImageName != nil) {
      item.customImageName = markerImageName;
    }

    [pointList addObject:item];
  }

  return pointList;
}

- (void)setMarkerImageName:(NSString *)markerImageName {
  _markerImageName = markerImageName;
  [self load];
}

- (void)setMarkerImageUrl:(NSString *)markerImageUrl {
  _markerImageUrl = markerImageUrl;
  [self load];
}

- (void)setMarkerList:(NSArray *)markerList {
  _markerList = markerList;
  [self load];
}

- (void)setCenterPoint:(NSDictionary *)centerPoint {
  _centerPoint = centerPoint;
  [self load];
}

- (void)load {
  double lat = 37.59523;
  double lng = 127.08600;
  if (_centerPoint != nil) {
    lat = [_centerPoint[@"lat"] doubleValue];
    lng = [_centerPoint[@"lng"] doubleValue];
  }

  [_mapView addPOIItems: createMarker(_markerList, _markerImageUrl, _markerImageName)];
  [_mapView setMapCenterPoint:[MTMapPoint mapPointWithGeoCoord:MTMapPointGeoMake(lat, lng)] animated:YES];

  [self addSubview: _mapView];
}

- (instancetype)init
{
  self = [super init];
  if (self) {
    _mapView = [[MTMapView alloc] initWithFrame:CGRectMake(0, 0, CGRectGetWidth(self.frame), CGRectGetHeight(self.frame))];
    _mapView.delegate = self;
    _mapView.baseMapType = MTMapTypeStandard;
    [_mapView fitMapViewAreaToShowAllPOIItems];

    [self load];
  }
  return self;
}

@end
