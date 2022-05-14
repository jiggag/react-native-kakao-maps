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
```tsx
import { KakakoMapView } from '@jiggag/react-native-kakao-maps';

return (
  <KakaoMapView
    markerImageName="customImageName" // 옵션1
    markerImageUrl="https://github.com/jiggag/react-native-kakao-maps/blob/develop/example/custom_image.png?raw=true" // 옵션2
    markerList={[
        {
          lat: 37.59523,
          lng: 127.08600,
          markerName: 'marker'
        },
        {
          lat: 37.59523,
          lng: 127.08705,
          markerName: 'marker2'
        },
    ]}
    width={300}
    height={500}
    centerPoint={{
        lat: 37.59523,
        lng: 127.08600,
    }}
  />
);
```

## Deprecated
```
KakakoMaps.show({...})
KakakoMaps.showKakaoMap({...})
```
