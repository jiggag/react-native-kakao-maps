
# react-native-kakao-maps

## Getting started
`yarn add @jiggag/react-native-kakao-maps`

## Env
### `iOS`
`=> Info.plist`
```xml
<key>KAKAO_APP_KEY</key>
<string>KAKAO_APP_KEY</string>
```
### `Android`
`=> /android/app/src/main/res/values/strings.xml`
```sh
<resources>
    ...
    <string name="kakao_app_key">KAKAO_APP_KEY</string>
</resources>
```

## Usage
```javascript
import KakakoMaps from '@jiggag/react-native-kakao-maps';

// v0.0.4~
KakakoMaps.show({
  markerList: [
    {
      markerName: 'Default Marker',
      lat: 37.537229,
      lng: 127.005515,
    },
    ...
  ],
});

KakakoMaps.show({
  markerList: [
    {
      markerName: 'Default Marker',
      lat: 37.537229,
      lng: 127.005515,
    },
    ...
  ],
  centerPoint: {
    lat: 37.5359,
    lng: 127.005518,
  },
});


// Deprecated (~v0.0.3)
KakakoMaps.showKakaoMap([
  {
    markerName: 'Default Marker',
    lat: 37.537229,
    lng: 127.005515,
  },
  ...
]);
```
