#import <React/RCTViewManager.h>
#import <MapViewController.h>

@interface KakaoMapManager : RCTViewManager
@end

@implementation KakaoMapManager

RCT_EXPORT_MODULE(KakaoMapView)

RCT_EXPORT_VIEW_PROPERTY(markerList, NSArray)
RCT_EXPORT_VIEW_PROPERTY(centerPoint, NSDictionary)
RCT_EXPORT_VIEW_PROPERTY(markerImageUrl, NSString)
RCT_EXPORT_VIEW_PROPERTY(markerImageName, NSString)
RCT_EXPORT_VIEW_PROPERTY(onChange, RCTBubblingEventBlock)

- (UIView *)view
{
  return [[MapViewController alloc] init];
}

@end
