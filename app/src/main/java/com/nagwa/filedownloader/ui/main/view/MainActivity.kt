package com.nagwa.filedownloader.ui.main.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import com.nagwa.filedownloader.R
import com.nagwa.filedownloader.base.extension.observe
import com.nagwa.filedownloader.base.network.model.FileResponseDto
import com.nagwa.filedownloader.base.view.BaseActivity
import com.nagwa.filedownloader.ui.main.viewmodel.MainViewModel
import com.nagwa.filedownloader.ui.main.viewmodel.MainViewModelFactory
import com.nagwa.filedownloader.utils.Constants
import com.nagwa.filedownloader.utils.crash.CrashReportActivity
import com.nagwa.filedownloader.utils.crash.HandleAppCrash
import javax.inject.Inject

class MainActivity(override val layoutResourceId: Int = R.layout.activity_main) : BaseActivity() {

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    @Inject
    lateinit var viewModel: MainViewModel

    lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        HandleAppCrash.deploy(this, CrashReportActivity::class.java)

        preferences = getSharedPreferences(
            Constants.APP_NAME,
            Context.MODE_PRIVATE
        )

        viewModel.fetchFiles()
    }

    override fun setUpViewModelStateObservers() {
        observe(viewModel.getState()) { onStateChanged(it) }
    }

    private fun onStateChanged(state: MainViewModel.State) = when (state) {
        is MainViewModel.State.ShowError -> {
        }
        MainViewModel.State.ShowLoading -> {
        }
        is MainViewModel.State.FileFetched -> showFiles(state.files)
    }

    private fun showFiles(files: List<FileResponseDto>) {
        Log.d("TAG", "showFiles: ${files.size}")

    }

}
