interface Coordinate {
  lat: number;
  lng: number;
}

interface Maker extends Coordinate {
  markerName: string;
}

export interface KakaoMapsPlugin {
  showKakaoMap(markerList: Maker[], initialCenterPoint?: Coordinate): void;
}

declare const RnKakaoMaps: KakaoMapsPlugin;

export default RnKakaoMaps;
