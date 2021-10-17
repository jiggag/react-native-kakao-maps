#import "RnKakaoMaps.h"
#import <React/RCTBridgeModule.h>
#import <React/RCTLog.h>
#import <MapViewController.h>

@implementation RnKakaoMaps

RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(show:(NSDictionary *) params)
{

  NSArray *markerList = [params objectForKey:@"markerList"];
  if (params.count == 0 || markerList == nil) {
    @throw([NSException exceptionWithName:@"Invalid parameter" reason:@"Invalid parameter" userInfo:nil]);
  }

  [self startKakaoMap:params];
}


- (void)startKakaoMap:(NSDictionary *)params {
  NSArray *markerList = [params objectForKey:@"markerList"];
  NSMutableDictionary *centerPoint =  [[NSMutableDictionary alloc] init];

  if ([params objectForKey:@"centerPoint"] == nil) {
    centerPoint[@"lat"] = @"37.537229";
    centerPoint[@"lng"] = @"127.005515";
  } else {
    centerPoint = [params objectForKey:@"centerPoint"];
  }

  dispatch_async(dispatch_get_main_queue(), ^{
    MapViewController *viewController = [[MapViewController alloc] init];
    viewController.title = @"RNKakaoMaps";
    viewController.modalPresentationStyle = UIModalPresentationFullScreen;
    [[[[RCTSharedApplication() delegate] window] rootViewController] presentViewController:viewController
                                                                                  animated:YES
                                                                                completion:nil];
  });

}


@end
