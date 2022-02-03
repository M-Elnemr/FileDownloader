package com.nagwa.filedownloader.base.view

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection

abstract class BaseActivity : AppCompatActivity() {

    @get:LayoutRes
    protected abstract val layoutResourceId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(layoutResourceId)
        setUpViewModelStateObservers()
    }

    abstract fun setUpViewModelStateObservers()

}