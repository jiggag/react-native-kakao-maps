#import "RnKakaoMaps.h"
#import <React/RCTBridgeModule.h>
#import <React/RCTLog.h>

@implementation RnKakaoMaps

RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(showKakaoMap:(NSArray *) gpsList:(RCTResponseSenderBlock) callback)
{
  RCTLogInfo(@"Pretending to create an event test");
  callback(@[]);
}

@end
