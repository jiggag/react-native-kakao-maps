import React, { useEffect, useRef } from 'react';
import { UIManager, findNodeHandle, requireNativeComponent, NativeModules } from 'react-native';

const { RnKakaoMaps } = NativeModules;

export default RnKakaoMaps;

const NativeComponent = requireNativeComponent('KakaoMapView');

const createFragment = (viewId) =>
  UIManager.dispatchViewManagerCommand(
    viewId,
    UIManager.KakaoMapView.Commands.create.toString(),
    [viewId]
  );

export const KakaoMapView = (props) => {
  const ref = useRef(null);

  useEffect(() => {
    const viewId = findNodeHandle(ref.current);
    createFragment(viewId);
  }, []);

  return (
    <NativeComponent
      {...props}
      ref={ref}
    />
  );
};
