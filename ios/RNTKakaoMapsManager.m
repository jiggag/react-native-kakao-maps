#import <React/RCTViewManager.h>
#import "MapView.h"

@interface RNTKakaoMapsManager : RCTViewManager

@end

@implementation RNTKakaoMapsManager

RCT_EXPORT_MODULE(RNTKakaoMaps);

RCT_EXPORT_VIEW_PROPERTY(markerList, NSArray*);
RCT_EXPORT_VIEW_PROPERTY(centerPoint, NSDictionary*);

- (UIView *)view
{
  return [[MapView alloc] init];
}

+ (BOOL)requiresMainQueueSetup {
    return YES;
}

@end
