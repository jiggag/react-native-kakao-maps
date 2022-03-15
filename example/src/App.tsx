import React, { useCallback } from 'react';
import { Button, SafeAreaView, ScrollView } from 'react-native';
import RnKakaoMaps from '@jiggag/react-native-kakao-maps';

export function App() {
  const onPress = useCallback(() => {
    RnKakaoMaps.show({
      markerList: [
        {
          markerName: 'Marker1',
          lat: 37.5359,
          lng: 127.005518,
        },
        {
          markerName: 'Marker2',
          lat: 37.5373,
          lng: 127.005515,
        },
      ],
      centerPoint: {
        lat: 37.5359,
        lng: 127.005518,
      },
    });
  }, []);

  return (
    <SafeAreaView>
      <ScrollView contentInsetAdjustmentBehavior="automatic">
        <Button title="Show" onPress={onPress} />
      </ScrollView>
    </SafeAreaView>
  );
}
