import Foundation

#if DEBUG
#if FB_SONARKIT_ENABLED
import FlipperKit
#endif
#endif

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {
  var window: UIWindow?
  var bridge: RCTBridge!

  private func initializeFlipper(with application: UIApplication) {
    #if DEBUG
    #if FB_SONARKIT_ENABLED
      let client = FlipperClient.shared()
      let layoutDescriptorMapper = SKDescriptorMapper(defaults: ())
      client?.add(FlipperKitLayoutPlugin(rootNode: application, with: layoutDescriptorMapper!))
      client?.add(FKUserDefaultsPlugin(suiteName: nil))
      client?.add(FlipperKitReactPlugin())
      client?.add(FlipperKitNetworkPlugin(networkAdapter: SKIOSNetworkAdapter()))
      client?.start()
    #endif
    #endif
  }

  func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {

    initializeFlipper(with: application)

    let jsCodeLocation: URL
      jsCodeLocation = RCTBundleURLProvider.sharedSettings().jsBundleURL(forBundleRoot: "index", fallbackResource:nil)
//    #if DEBUG
//      jsCodeLocation = RCTBundleURLProvider.sharedSettings().jsBundleURL(forBundleRoot: "index", fallbackResource:nil)
//    #else
//    // TODO codepush
//      jsCodeLocation = NSBundle.mainBundle().URLForResource("main", withExtension: "jsbundle")
//    #endif

    let rootView = RCTRootView(bundleURL: jsCodeLocation, moduleName: "RNStarter", initialProperties: nil, launchOptions: launchOptions)

    if #available(iOS 13.0, *) {
      rootView.backgroundColor = UIColor.systemBackground
    } else {
      rootView.backgroundColor = UIColor.white
    }

    let rootViewController = UIViewController()
    rootViewController.view = rootView

    self.window = UIWindow(frame: UIScreen.main.bounds)
    self.window?.rootViewController = rootViewController
    self.window?.makeKeyAndVisible()

    return true
  }
}
