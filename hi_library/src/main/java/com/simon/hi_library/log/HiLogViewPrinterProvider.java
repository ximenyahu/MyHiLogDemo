package com.simon.hi_library.log;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.simon.hi_library.R;
import com.simon.hi_library.util.HiDisplayUtil;

public class HiLogViewPrinterProvider {
    private static final String TAG_FLOATING_VIEW = "TAG_FLOATING_VIEW";
    private static final String TAG_LOG_VIEW = "TAG_LOG_VIEW";

    private View floatingView;
    private boolean isOpen;

    private FrameLayout rootView;
    private RecyclerView recyclerView;
    private View logView;

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
        params.gravity = Gravity.BOTTOM | Gravity.END;
        View floatingView = genFloatingView();
        params.bottomMargin = HiDisplayUtil.dp2px(100, rootView.getResources());
        rootView.addView(floatingView, params);
    }

    private View genFloatingView() {
        if (floatingView != null) {
            return floatingView;
        }
        View floatingView = LayoutInflater.from(rootView.getContext()).inflate(R.layout.hilog_open,
                null, false);
        TextView tvOpen = floatingView.findViewById(R.id.tv_open);
        tvOpen.setOnClickListener(v -> {
            showLogView();
        });
        return this.floatingView = floatingView;
    }

    private View genLogView() {
        if (logView != null) {
            return logView;
        }
        View logView = LayoutInflater.from(rootView.getContext()).inflate(R.layout.hilog_clear_close, null, false);
        TextView tvClose = logView.findViewById(R.id.tv_close);
        TextView tvClear = logView.findViewById(R.id.tv_clear);
        tvClose.setOnClickListener(v -> {
            closeLogView();
        });

        tvClear.setOnClickListener(v -> {
            clearLogView();
        });
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

    /**
     * 清空Log
     */
    private void clearLogView() {
        recyclerView.removeAllViews();
    }
}
