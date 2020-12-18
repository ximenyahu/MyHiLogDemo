package com.simon.hi_library.log;

public class HiLogManager {
    private HiLogConfig hiLogConfig;
    private static HiLogManager instance;

    private HiLogManager(HiLogConfig hiLogConfig) {
        this.hiLogConfig = hiLogConfig;
    }

    public static HiLogManager getInstance() {
        return instance;
    }

    public static void init(HiLogConfig config) {
        instance = new HiLogManager(config);
    }

    public HiLogConfig getHiLogConfig() {
        return hiLogConfig;
    }
}
