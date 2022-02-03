package com.nagwa.filedownloader.ui.main.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.nagwa.filedownloader.ui.main.domain.FetchFilesUseCase
import com.nagwa.filedownloader.ui.main.domain.FetchFilesUseCaseImpl

class MainViewModelImpl(
    application: Application,
    private val state: MediatorLiveData<State>,
    private val fetchFilesUseCaseImpl: FetchFilesUseCaseImpl
) :
    MainViewModel(application) {

    init {
        state.addSource(fetchFilesUseCaseImpl.getLiveData(), ::onFilesFetched)
    }

    private fun onFilesFetched(result: FetchFilesUseCase.Result?) {
        when (result) {
            is FetchFilesUseCase.Result.OnError -> state.value = State.ShowError(result.error)
            is FetchFilesUseCase.Result.OnSuccess -> state.value =
                State.FileFetched(result.files)
        }
    }

    override fun getState(): LiveData<State> = state

    override fun fetchFiles() {
        state.value = State.ShowLoading
        fetchFilesUseCaseImpl.execute()
    }

}
