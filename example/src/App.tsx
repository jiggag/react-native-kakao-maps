import React from 'react';
import { SafeAreaView, ScrollView} from 'react-native';
import { KakaoMapView } from '@jiggag/react-native-kakao-maps';

export function App() {
  return (
    <SafeAreaView>
      <ScrollView contentInsetAdjustmentBehavior="automatic">
        <KakaoMapView style={{ width: 300, height: 300 }}  />
      </ScrollView>
    </SafeAreaView>
  );
}
