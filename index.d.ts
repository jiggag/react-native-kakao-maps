interface Coordinate {
  lat: number;
  lng: number;
}

interface Maker extends Coordinate {
  markerName: string;
}

interface Params {
   markerList: Maker[];
   centerPoint?: Coordinate;
}

export interface KakaoMapsPlugin {
  // Deprecated
  showKakaoMap(markerList: Maker[]): void;

  show(params: Params): void;
}

declare const RnKakaoMaps: KakaoMapsPlugin;

export default RnKakaoMaps;
