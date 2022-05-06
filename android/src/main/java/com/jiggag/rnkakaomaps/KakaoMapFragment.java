package com.jiggag.rnkakaomaps;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;

public class KakaoMapFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO
        ArrayList markerList = new ArrayList();
        HashMap<String, Object> centerPoint = new HashMap<>();
        centerPoint.put(Constants.PARAM_LAT, Constants.INIT_LAT);
        centerPoint.put(Constants.PARAM_LNG, Constants.INIT_LNG);

        Intent intent = new Intent();
        intent.setClass(getActivity(), MapViewActivity.class);
        intent.putExtra("markerList", markerList);
        intent.putExtra("centerPoint", centerPoint);
//        intent.putExtra("markerImageName", markerImageName);
//        intent.putExtra("markerImageUrl", markerImageUrl);
        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        super.onCreateView(inflater, parent, savedInstanceState);

        return inflater.inflate(R.layout.kakao_map_view, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}