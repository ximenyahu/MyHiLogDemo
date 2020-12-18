package com.simon.hi_library.log;

import java.util.ArrayList;
import java.util.List;

public abstract class HiLogConfig {
    public static final int MAX_LEN = 512;
    public static HiLogThreadFormat HI_LOG_THREAD_FORMAT = new HiLogThreadFormat();
    public static HiLogStaceTraceFormat HI_LOG_STACE_TRACE_FORMAT = new HiLogStaceTraceFormat();
    private List<IHiLogPrinter> printers = new ArrayList<>();

    public String getGlobalTag() {
        return "HiLog";
    }

    public boolean enable() {
        return true;
    }

    public boolean includeThread() {
        return true;
    }

    public int stackTraceDepth() {
        return 5;
    }


    public JsonParser injectJsonParser() {
        return null;
    }

    public interface JsonParser {
        String toString(Object src);
    }

    public List<IHiLogPrinter> getPrinters() {
        return printers;
    }

    public void addPrinter(IHiLogPrinter printer) {
        printers.add(printer);
    }

    public void removePrinter(IHiLogPrinter printer) {
        printers.remove(printer);
    }
}
