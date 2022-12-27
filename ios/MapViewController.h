#import <UIKit/UIKit.h>
#import <DaumMap/MTMapView.h>
#import <React/RCTComponent.h>

@interface MapViewController : UIView

@property(nonatomic, assign, nonnull) NSArray *markerList;
@property(nonatomic, assign) double lat;
@property(nonatomic, assign) double lng;
@property(nonatomic, assign, nonnull) NSString *markerImageUrl;
@property(nonatomic, assign, nonnull) NSString *markerImageName;
@property (nonatomic, copy) RCTBubblingEventBlock onChange;

@end
