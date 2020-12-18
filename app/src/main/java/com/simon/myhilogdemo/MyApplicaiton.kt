package com.simon.myhilogdemo

import android.app.Application
import com.google.gson.Gson
import com.simon.hi_library.log.HiLogConfig
import com.simon.hi_library.log.HiLogManager

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        HiLogManager.init(object : HiLogConfig() {
            override fun includeThread(): Boolean {
                return true;
            }

            override fun getGlobalTag(): String {
                return "MyApplication"
            }

            override fun stackTraceDepth(): Int {
                return 5;
            }

//            override fun injectJsonParser(): JsonParser {
//                return JsonParser {
//                    Gson().toJson(it)
//                }
//            }
        })
    }
}