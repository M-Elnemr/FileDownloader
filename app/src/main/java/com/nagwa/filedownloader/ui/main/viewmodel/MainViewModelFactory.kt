package com.nagwa.filedownloader.ui.main.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nagwa.filedownloader.app.MainApp
import com.nagwa.filedownloader.ui.main.domain.FetchFilesUseCaseImpl
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
    private val application: MainApp,
    private val fetchFilesUseCaseImpl: FetchFilesUseCaseImpl
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(p0: Class<T>): T {
        return MainViewModelImpl(
            application,
            MediatorLiveData(),
            fetchFilesUseCaseImpl
        ) as T
    }
}
