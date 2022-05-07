import * as React from 'react';

interface Coordinate {
  lat: number;
  lng: number;
}

interface Maker extends Coordinate {
  markerName: string;
}

interface Params {
  markerImageName?: string;
  markerImageUrl?: string;
  markerList: Maker[];
  centerPoint?: Coordinate;
}

interface ComponentProps extends Params {
  width: number;
  height: number;
}

export interface KakaoMapsPlugin {
  // Deprecated
  showKakaoMap(markerList: Maker[]): void;

  show(params: Params): void;
}

declare const RnKakaoMaps: KakaoMapsPlugin;

export default RnKakaoMaps;

declare const KakaoMapView: React.ComponentType<ComponentProps>;

export {
  KakaoMapView
};
