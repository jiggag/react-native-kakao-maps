import * as React from 'react';

interface Coordinate {
  lat: number;
  lng: number;
}

interface Maker extends Coordinate {
  markerName: string;
}

interface MapNativeEvent {
  nativeEvent: {
    lat: number;
    lng: number;
    zoomLevel: number;
  }
}

interface Params {
  markerImageName?: string;
  markerImageUrl?: string;
  markerList: Maker[];
  centerPoint?: Coordinate;
  onChange: (event: MapNativeEvent) => void;
}

interface ComponentProps extends Params {
  width: number;
  height: number;
}

declare const KakaoMapView: React.ComponentType<ComponentProps>;

export {
  KakaoMapView
};
