package com.simon.hi_library.log;

import android.util.Log;

import androidx.annotation.NonNull;

import static com.simon.hi_library.log.HiLogConfig.MAX_LEN;

public class HiLogConsolePrinter implements IHiLogPrinter {

    @Override
    public void print(@NonNull HiLogConfig config, int level, String tag, @NonNull String printString) {
        int countOfSub = printString.length() / MAX_LEN;
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (int i = 0; i < countOfSub; i++) {
            sb.append(printString.substring(index, index + MAX_LEN)).append(";\n");
        }
        Log.println(level, tag, printString.substring(countOfSub * MAX_LEN, printString.length() - 1));
    }
}
