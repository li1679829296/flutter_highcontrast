#import "FlutterHighcontrastPlugin.h"
#if __has_include(<flutter_highcontrast/flutter_highcontrast-Swift.h>)
#import <flutter_highcontrast/flutter_highcontrast-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "flutter_highcontrast-Swift.h"
#endif

@implementation FlutterHighcontrastPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFlutterHighcontrastPlugin registerWithRegistrar:registrar];
}
@end
