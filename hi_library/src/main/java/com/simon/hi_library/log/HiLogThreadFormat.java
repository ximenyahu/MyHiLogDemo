package com.simon.hi_library.log;

public class HiLogThreadFormat implements IHiLogFormat<Thread> {
    @Override
    public String format(Thread data) {
        return "Thread: " + data.getName();
    }


}
