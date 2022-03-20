# react-native-kakao-maps

## Getting started
`yarn add @jiggag/react-native-kakao-maps`

## Env
### 카카오 앱 키 설정
#### 안드로이드
- `./android/app/src/main/res/values/strings.xml`
  ```sh
  <resources>
      ...
      <string name="kakao_app_key">KAKAO_APP_KEY</string>
  </resources>
  ```
#### iOS
- `./Info.plist`
  ```xml
  <key>KAKAO_APP_KEY</key>
  <string>KAKAO_APP_KEY</string>
  ```

### 커스텀 마커 이미지
1. 마커 이미지 URL `markerImageUrl` 옵션으로 설정하여 사용
2. 마커 이미지 등록 후 `markerImageName` 옵션으로 `{이미지}` 호출하여 사용
    ```
    - 안드로이드: ./android/app/src/main/res/drawable/{이미지}.png
    - iOS: xcode 프로젝트 폴더에 {이미지}.png 추가 + Copy items if needed
    ```
   
## Usage
```javascript
import KakakoMaps from '@jiggag/react-native-kakao-maps';

// v0.0.6~
KakakoMaps.show({
  markerImageName: 'customImageName', // 옵션1
  markerImageUrl: 'https://..../customImageUrl.png', // 옵션2
  markerList: [
    {
      markerName: 'Default Marker',
      lat: 37.537229,
      lng: 127.005515,
    },
    ...
  ],
});

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
