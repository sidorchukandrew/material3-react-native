#import <React/RCTViewManager.h>
#import <React/RCTUIManager.h>
#import "RCTBridge.h"

@interface ReactNativeViewManager : RCTViewManager
@end

@implementation ReactNativeViewManager

RCT_EXPORT_MODULE(ReactNativeView)

- (UIView *)view
{
  return [[UIView alloc] init];
}

RCT_EXPORT_VIEW_PROPERTY(color, NSString)

@end
