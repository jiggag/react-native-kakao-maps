import React, { useEffect, useRef } from 'react';
import { Platform, UIManager, findNodeHandle, requireNativeComponent } from 'react-native';

const NativeComponent = requireNativeComponent('KakaoMapView');

const createFragment = viewId =>
  UIManager.dispatchViewManagerCommand(
    viewId,
    UIManager.KakaoMapView.Commands.create.toString(),
    [viewId],
  );

export const KakaoMapView = props => {
  const ref = useRef(null);

  useEffect(() => {
    const viewId = findNodeHandle(ref.current);

    if (Platform.OS === 'android') {
      createFragment(viewId);
    }
  }, []);

  return <NativeComponent {...props} ref={ref} />;
};
