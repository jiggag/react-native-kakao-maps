#import <UIKit/UIKit.h>
#import <DaumMap/MTMapView.h>


@interface MapViewController : UIViewController

@property(assign, nonnull) NSArray *markerList;
@property(assign, nonnull) NSDictionary *centerPoint;
@property(assign, nonnull) NSString *markerImageUrl;

@end
