package com.nagwa.filedownloader.ui.main.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import com.nagwa.filedownloader.base.network.model.FileResponseDto
import com.nagwa.filedownloader.base.viewModel.BaseViewModel

abstract class MainViewModel(application: Application) : BaseViewModel(application) {

    sealed class State {
        data class FileFetched(val files: List<FileResponseDto>) : State()
        object ShowLoading : State()
        data class ShowError(val error: String) : State()
    }

    abstract fun getState(): LiveData<State>

    abstract fun fetchFiles()
}
