package com.simon.myhilogdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.simon.hi_library.log.*

class MainActivity : AppCompatActivity() {
    var hiLogViewPrinter: HiLogViewPrinter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hiLogViewPrinter = HiLogViewPrinter(this)
        findViewById<Button>(R.id.btn_log).setOnClickListener {
            showViewLog();
        }
        hiLogViewPrinter!!.printerProvider.showFloatingView()
    }

    private fun showViewLog() {
        HiLogManager.getInstance().hiLogConfig.addPrinter(hiLogViewPrinter);
        HiLog.log(object : HiLogConfig() {
            override fun includeThread(): Boolean {
                return false
            }
        }, HiLogType.E, "---", "5566")
        HiLog.a("9900")
    }
}