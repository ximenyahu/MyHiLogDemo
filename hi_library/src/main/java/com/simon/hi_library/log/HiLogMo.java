package com.simon.hi_library.log;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class HiLogMo {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss", Locale.CHINA);
    public long timeMills;
    public int level;
    public String tag;
    public String log;

    public HiLogMo(long timeMills, int level, String tag, String log) {
        this.timeMills = timeMills;
        this.level = level;
        this.tag = tag;
        this.log = log;
    }

    public String getFlattedLog() {
        return getFlatted() + "\n" + log;
    }

    public String getFlatted() {
        return formatTime(timeMills) + "|" + level + "|" + tag + "|";
    }

    private String formatTime(long timeMills) {
        return sdf.format(timeMills);
    }
}
