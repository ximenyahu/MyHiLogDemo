package com.simon.hi_library.log;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.simon.hi_library.util.HiDisplayUtil;

public class HiLogViewPrinterProvider {
    private static final String TAG_FLOATING_VIEW = "TAG_FLOATING_VIEW";
    private static final String TAG_LOG_VIEW = "TAG_LOG_VIEW";

    private View floatingView;
    private boolean isOpen;

    private FrameLayout rootView;
    private RecyclerView recyclerView;
    private FrameLayout logView;

    public HiLogViewPrinterProvider(FrameLayout rootView, RecyclerView recyclerView) {
        this.rootView = rootView;
        this.recyclerView = recyclerView;
    }

    public void showFloatingView() {
        if (rootView.findViewWithTag(TAG_FLOATING_VIEW) != null) {
            return;
        }
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMarginEnd(4);
        params.gravity = Gravity.BOTTOM | Gravity.END;
        View floatingView = genFloatingView();
        floatingView.setBackgroundColor(Color.BLACK);
        floatingView.setAlpha(0.8f);
        params.bottomMargin = HiDisplayUtil.dp2px(100, rootView.getResources());
        rootView.addView(floatingView, params);
    }

    private View genFloatingView() {
        if (floatingView != null) {
            return floatingView;
        }
        TextView textView = new TextView(rootView.getContext());
        textView.setOnClickListener(v -> {
            if (!isOpen) {
                showLogView();
            }
        });
        textView.setTextSize(20);
        textView.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textView.setText("HiLog");
        return floatingView = textView;
    }

    private View genLogView() {
        if (logView != null) {
            return logView;
        }
        FrameLayout logView = new FrameLayout(rootView.getContext());
        logView.setBackgroundColor(Color.BLACK);
        logView.addView(recyclerView);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.END;
        TextView tvClose = new TextView(rootView.getContext());
        tvClose.setTextSize(20);
        tvClose.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tvClose.setText("Close");
        tvClose.setOnClickListener(v -> {
            closeLogView();
        });
        logView.addView(tvClose, params);
        return this.logView = logView;
    }

    /**
     * 展示Log View
     */
    private void showLogView() {
        if (rootView.findViewWithTag(TAG_LOG_VIEW) != null) {
            return;
        }
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, HiDisplayUtil.dp2px(160, rootView.getResources()));
        params.gravity = Gravity.BOTTOM;
        View view = genLogView();
        view.setTag(TAG_LOG_VIEW);
        rootView.addView(view, params);
        isOpen = true;
    }

    /**
     * 关闭LogView
     */
    private void closeLogView() {
        isOpen = false;
        rootView.removeView(genLogView());
    }
}
