package com.simon.hi_library.log;

import androidx.annotation.NonNull;

public interface IHiLogPrinter {
    void print(@NonNull HiLogConfig config, int level, String tag, @NonNull String printString);
}
