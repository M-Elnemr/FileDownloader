package com.nagwa.filedownloader.utils.crash

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.nagwa.filedownloader.R
import com.nagwa.filedownloader.base.network.api.ApiInterface
import com.nagwa.filedownloader.base.view.BaseActivity
import com.nagwa.filedownloader.ui.main.view.MainActivity
import javax.inject.Inject

class CrashReportActivity(override val layoutResourceId: Int = R.layout.activity_crash_report) :
    BaseActivity() {

    @Inject
    lateinit var apiInterface: ApiInterface

    private var _btnclose: Button? = null
    private var _tv_crash: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        println(intent.getStringExtra("stackTrace"))
        sendError(intent.getStringExtra("stackTrace").toString())
        _btnclose = findViewById<View>(R.id.btnclose) as Button
        _tv_crash = findViewById(R.id.tv_crash)
        _btnclose!!.setOnClickListener {
            startActivity(Intent(this@CrashReportActivity, MainActivity::class.java))
            finish()
        }
    }

    override fun setUpViewModelStateObservers() {}

    private fun sendError(report: String) {
    }
}