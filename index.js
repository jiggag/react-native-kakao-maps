import React, { useEffect, useRef } from 'react';
import { Platform, UIManager, findNodeHandle, requireNativeComponent, View } from 'react-native';

const NativeComponent = requireNativeComponent('KakaoMapView');

const register = (ref) => {
  if (Platform.OS === 'ios') {
    return;
  }

  const viewId = findNodeHandle(ref.current);
  return UIManager.dispatchViewManagerCommand(
    viewId,
    UIManager.KakaoMapView.Commands.create.toString(),
    [viewId]
  );
};

export const KakaoMapView = (props) => {
  const ref = useRef(null);

  useEffect(() => {
    register(ref);
  }, []);

  return (
    <View style={{
      borderColor: 'transparent'
    }}>
      <NativeComponent
        {...props}
        ref={ref}
      />
    </View>
  );
};
