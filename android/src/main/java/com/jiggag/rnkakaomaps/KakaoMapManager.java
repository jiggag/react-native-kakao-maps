package com.jiggag.rnkakaomaps;

import android.view.Choreographer;
import android.view.View;
import android.widget.FrameLayout;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ThemedReactContext;

import java.util.Map;

public class KakaoMapManager extends ViewGroupManager<FrameLayout> {

    public static final String REACT_CLASS = "KakaoMapView";
    public final int COMMAND_CREATE = 1;
    private int propWidth;
    private int propHeight;

    ReactApplicationContext reactContext;

    public KakaoMapManager(ReactApplicationContext reactContext) {
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    public FrameLayout createViewInstance(ThemedReactContext reactContext) {
        return new FrameLayout(reactContext);
    }

    @Nullable
    @Override
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of("create", COMMAND_CREATE);
    }

    @Override
    public void receiveCommand(
            @NonNull FrameLayout root,
            String commandId,
            @Nullable ReadableArray args
    ) {
        super.receiveCommand(root, commandId, args);
        int reactNativeViewId = args.getInt(0);
        int commandIdInt = Integer.parseInt(commandId);

        switch (commandIdInt) {
            case COMMAND_CREATE:
                createFragment(root, reactNativeViewId);
                break;
            default: {}
        }
    }

    @ReactPropGroup(names = {"width", "height"}, customType = "Style")
    public void setStyle(FrameLayout view, int index, Integer value) {
        if (index == 0) {
            propWidth = value;
        }

        if (index == 1) {
            propHeight = value;
        }
    }

    @ReactProp(name = "markerList")
    public void setMarkerList(FrameLayout view, @Nullable ReadableArray markerList) {
    }

    @ReactProp(name = "centerPoint")
    public void setCenterPoint(FrameLayout view, @Nullable ReadableMap centerPoint) {
    }

    @ReactProp(name = "markerImageName")
    public void setMarkerImageName(FrameLayout view, @Nullable String markerImageName) {
    }

    @ReactProp(name = "markerImageUrl")
    public void setMarkerImageUrl(FrameLayout view, @Nullable String markerImageUrl) {
    }

    public void createFragment(FrameLayout root, int reactNativeViewId) {
        ViewGroup parentView = (ViewGroup) root.findViewById(reactNativeViewId);
        setupLayout(parentView);

        final KakaoMapFragment fragment = new KakaoMapFragment();
        FragmentActivity activity = (FragmentActivity) reactContext.getCurrentActivity();

        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(reactNativeViewId, fragment, String.valueOf(reactNativeViewId))
                .commit();
    }

    public void setupLayout(View view) {
        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
            @Override
            public void doFrame(long frameTimeNanos) {
                manuallyLayoutChildren(view);
                view.getViewTreeObserver().dispatchOnGlobalLayout();
                Choreographer.getInstance().postFrameCallback(this);
            }
        });
    }

    public void manuallyLayoutChildren(View view) {
        // propWidth and propHeight coming from react-native props
        int width = propWidth;
        int height = propHeight;

        view.measure(
                View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY));

        view.layout(0, 0, width, height);
    }
}
