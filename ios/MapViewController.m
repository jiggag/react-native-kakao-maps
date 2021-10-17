#import "MapViewController.h"

@interface MapViewController () {
    MTMapView *_mapView;
    BOOL isMapRotationUsing;
}

@end


@implementation MapViewController

- (void)viewDidLoad {
  [super viewDidLoad];
  _mapView = [[MTMapView alloc] initWithFrame:CGRectMake(0, 0, CGRectGetWidth(self.view.frame), CGRectGetHeight(self.view.frame))];
  _mapView.delegate = self;
  _mapView.baseMapType = MTMapTypeStandard;
  [self.view addSubview:_mapView];
}

- (void)viewWillDisappear:(BOOL)animated {
    if (_mapView != nil) {
        _mapView = nil;
    }
}

#pragma mark -
#pragma mark Event Handlers

- (void)_onClickMenuButton:(id)sender {
  return;
}

@end
