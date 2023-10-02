import React, { useCallback } from 'react';
import { SafeAreaView } from 'react-native';
import { KakaoMapView } from '@jiggag/react-native-kakao-maps';

const Constants = {
  CENTER_POINT: {
    lat: 37.59523,
    lng: 127.086,
  },
  MARKER_LIST: [
    {
      lat: 37.59523,
      lng: 127.086,
      markerName: 'marker',
    },
    {
      lat: 37.59523,
      lng: 127.08705,
      markerName: 'marker2',
    },
  ],
};

const App = () => {
  const onChange = useCallback(event => {
    console.log('[onChange]', event.nativeEvent);
  }, []);

  return (
    <SafeAreaView>
      <KakaoMapView
        markerImageUrl="https://github.com/jiggag/react-native-kakao-maps/blob/develop/example/custom_image.png?raw=true"
        markerList={Constants.MARKER_LIST}
        width={300}
        height={500}
        centerPoint={Constants.CENTER_POINT}
        onChange={onChange}
      />
    </SafeAreaView>
  );
};

export default App;
