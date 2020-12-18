package com.simon.hi_library.log;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.List;

public class HiLog {
    public static void v(Object... contents) {
        log(HiLogType.V, contents);
    }

    public static void vt(String tag, Object... contents) {
        log(HiLogType.V, tag, contents);
    }

    public static void d(Object... contents) {
        log(HiLogType.D, contents);
    }

    public static void dt(String tag, Object... contents) {
        log(HiLogType.D, tag, contents);
    }

    public static void i(Object... contents) {
        log(HiLogType.I, contents);
    }

    public static void it(String tag, Object... contents) {
        log(HiLogType.I, tag, contents);
    }

    public static void w(Object... contents) {
        log(HiLogType.W, contents);
    }

    public static void wt(String tag, Object... contents) {
        log(HiLogType.W, tag, contents);
    }

    public static void e(Object... contents) {
        log(HiLogType.E, contents);
    }

    public static void et(String tag, Object... contents) {
        log(HiLogType.E, tag, contents);
    }

    public static void a(Object... contents) {
        log(HiLogType.A, contents);
    }

    public static void at(String tag, Object... contents) {
        log(HiLogType.A, tag, contents);
    }

    public static void log(@HiLogType.Type int type, Object... contents) {
        log(type, HiLogManager.getInstance().getHiLogConfig().getGlobalTag(), contents);
    }

    public static void log(@HiLogType.Type int type, @NonNull String tag, Object... contents) {
        log(HiLogManager.getInstance().getHiLogConfig(), type, tag, contents);
    }


    public static void log(HiLogConfig config, @HiLogType.Type int type, @NonNull String tag, Object... contents) {
        if (!config.enable()) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        if (config.includeThread()) {
            String threadInfo = HiLogConfig.HI_LOG_THREAD_FORMAT.format(Thread.currentThread());
            sb.append(threadInfo).append("\n");
        }

        if (config.stackTraceDepth() > 0) {

        }

        String body = parse(config, contents);
        sb.append(body);
        List<IHiLogPrinter> printers = config.getPrinters();
        for (IHiLogPrinter printer : printers) {
            printer.print(config, type, tag, sb.toString());
        }
    }

    private static String parse(HiLogConfig config, Object... contents) {
        StringBuilder sb = new StringBuilder();
        if (config.injectJsonParser() != null) {
            //只有一个数据且为String的情况下，不再进行序列化
            if (contents.length == 1 && contents[0] instanceof String) {
                return (String) contents[0];
            }
            return config.injectJsonParser().toString(contents);
        }
        for (Object o : contents) {
            sb.append(o.toString()).append(";");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

}
