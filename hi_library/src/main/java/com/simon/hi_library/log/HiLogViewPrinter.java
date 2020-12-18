package com.simon.hi_library.log;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.simon.hi_library.R;

import java.util.ArrayList;
import java.util.List;

public class HiLogViewPrinter implements IHiLogPrinter {

    private final RecyclerView recyclerView;
    private final LogAdapter adapter;
    private HiLogViewPrinterProvider printerProvider;

    @Override
    public void print(@NonNull HiLogConfig config, int level, String tag, @NonNull String printString) {
        //将log添加到recyclerview上
        adapter.addItem(new HiLogMo(System.currentTimeMillis(), level, tag, printString));
        recyclerView.smoothScrollToPosition(adapter.getItemCount() - 1);
    }

    public HiLogViewPrinter(Activity activity) {
        FrameLayout rootView = activity.findViewById(android.R.id.content);
        recyclerView = new RecyclerView(activity);
        adapter = new LogAdapter(activity);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(adapter);
        printerProvider = new HiLogViewPrinterProvider(rootView, recyclerView);
    }

    @NonNull
    public HiLogViewPrinterProvider getPrinterProvider() {
        return printerProvider;
    }

    private class LogAdapter extends RecyclerView.Adapter<LogViewHolder> {

        private final LayoutInflater inflater;
        private List<HiLogMo> logs = new ArrayList<>();

        public LogAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        public void addItem(HiLogMo hiLogMo) {
            logs.add(hiLogMo);
            notifyItemInserted(logs.size() - 1);
        }


        @NonNull
        @Override
        public LogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.hilog_item, parent, false);
            return new LogViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull LogViewHolder holder, int position) {
            HiLogMo logMo = logs.get(position);

            int color = getHighLightColor(logMo.level);
            holder.tag.setTextColor(color);
            holder.message.setTextColor(color);

            holder.tag.setText(logMo.tag);
            holder.message.setText(logMo.log);
        }

        private int getHighLightColor(int level) {
            int highlight;
            switch (level) {
                case HiLogType.V:
                    highlight = 0xffbbbbbb;
                    break;
                case HiLogType.D:
                    highlight = 0xffffffff;
                    break;
                case HiLogType.I:
                    highlight = 0xff6a8759;
                    break;
                case HiLogType.W:
                    highlight = 0xffbbb529;
                    break;
                case HiLogType.E:
                    highlight = 0xffff6b68;
                    break;
                default:
                    highlight = 0xffffff00;
                    break;
            }
            return highlight;
        }

        @Override
        public int getItemCount() {
            return logs.size();
        }
    }

    private class LogViewHolder extends RecyclerView.ViewHolder {

        private final TextView tag;
        private final TextView message;

        public LogViewHolder(@NonNull View itemView) {
            super(itemView);
            tag = itemView.findViewById(R.id.tag);
            message = itemView.findViewById(R.id.message);
        }
    }
}
