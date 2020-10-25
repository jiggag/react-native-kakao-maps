interface Maker {
  markerName: string;
  lat: number;
  lng: number;
}

export interface KakaoMapsPlugin {
  showKakaoMap(markerList: Maker[]): void;
}

declare const RnKakaoMaps: KakaoMapsPlugin;

export default RnKakaoMaps;
