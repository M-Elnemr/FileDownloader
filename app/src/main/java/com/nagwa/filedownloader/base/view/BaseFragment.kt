package com.nagwa.filedownloader.base.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import dagger.android.support.DaggerFragment

abstract class BaseFragment : DaggerFragment() {

    @get:LayoutRes
    protected abstract val layoutResourceId: Int

    lateinit var rootView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpViewSliceActionObservers()
        setUpViewModelStateObservers()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rootView = inflater.inflate(layoutResourceId, container, false)

        initViewSlices()

        return rootView
    }

    abstract fun setUpViewSliceActionObservers()

    abstract fun setUpViewModelStateObservers()

    abstract fun initViewSlices()

}