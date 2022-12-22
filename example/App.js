import React from 'react';
import { SafeAreaView } from 'react-native';
import { KakaoMapView } from '@jiggag/react-native-kakao-maps';

const App = () => {
  return (
    <SafeAreaView>
      <KakaoMapView
        markerImageUrl="https://github.com/jiggag/react-native-kakao-maps/blob/develop/example/custom_image.png?raw=true"
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
    </SafeAreaView>
  );
};

export default App;
