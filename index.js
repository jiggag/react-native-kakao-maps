import React, { useEffect, useRef } from 'react';
import { Platform, UIManager, findNodeHandle, requireNativeComponent, View } from 'react-native';

const NativeComponent = requireNativeComponent('KakaoMapView');

const createFragment = (viewId) => {
  if (Platform.OS === 'ios') {
    return;
  }
  return UIManager.dispatchViewManagerCommand(
    viewId,
    UIManager.KakaoMapView.Commands.create.toString(),
    [viewId]
  );
};

export const KakaoMapView = (props) => {
  const ref = useRef(null);

  useEffect(() => {
    const viewId = findNodeHandle(ref.current);
    createFragment(viewId);
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
