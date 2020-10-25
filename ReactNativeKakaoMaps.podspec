require 'json'

# Returns the version number for a package.json file
pkg_version = lambda do |dir_from_root = '', version = 'version'|
  path = File.join(__dir__, dir_from_root, 'package.json')
  JSON.parse(File.read(path))[version]
end

# Let the main package.json decide the version number for the pod
package_version = pkg_version.call

Pod::Spec.new do |s|
  s.name         = "ReactNativeKakaoMaps"
  s.version      = package_version
  s.summary      = "A react native module kakao maps"
  s.description  = <<-DESC
                   A react native module kakao maps
                   DESC
  s.homepage     = "https://github.com/jiggag/react-native-kakao-maps"
  s.license      = "MIT"
  s.author       = { "Atul R" => "jiggag90@gmail.com" }
  s.platform     = :ios, "9.0"
  s.source       = { :git => "https://github.com/jiggag/react-native-kakao-maps.git", :tag => s.version.to_s }
  s.source_files  = "ios/*.{h,m}"

  s.dependency "React"

end
