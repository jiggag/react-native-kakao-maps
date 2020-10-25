
# react-native-kakao-maps

## Getting started
`yarn add @jiggag/react-native-kakao-maps`

## Env
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

const markerList = [
  {
    markerName: 'Default Marker',
    lat: 37.537229,
    lng: 127.005515,
  }
];

...

KakakoMaps.showKakaoMap(markerList);
```

## Reference
[React Native Exception Handler](https://github.com/master-atul/react-native-exception-handler)