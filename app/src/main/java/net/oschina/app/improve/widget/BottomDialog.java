package net.oschina.app.improve.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by thanatosx
 * on 2016/12/23.
 */

public class BottomDialog extends BottomSheetDialog {

    private BottomSheetBehavior behavior;
    private boolean isTranslucentStatus;

    public BottomDialog(@NonNull Context context, boolean isTranslucentStatus) {
        super(context);
        this.isTranslucentStatus = isTranslucentStatus;
        Window window = getWindow();
        if (window != null) {
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            if (isTranslucentStatus)
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        initialize(view);
    }

    @Override
    public void show() {
        super.show();
        //behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    private void initialize(final View view) {
        ViewGroup parent = (ViewGroup) view.getParent();
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) parent.getLayoutParams();
        behavior = (BottomSheetBehavior) params.getBehavior();
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    dismiss();
                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    //TDevice.hideSoftKeyboard(view);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });
    }
}
