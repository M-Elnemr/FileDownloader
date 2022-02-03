package com.nagwa.filedownloader.ui.main.domain

import com.nagwa.filedownloader.base.domain.BaseUseCase
import com.nagwa.filedownloader.base.network.model.FileResponseDto
import com.nagwa.filedownloader.ui.main.domain.FetchFilesUseCase.Result
import com.nagwa.filedownloader.ui.main.repository.MainRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FetchFilesUseCaseImpl @Inject constructor(
    private val repository: MainRepository
) : FetchFilesUseCase, BaseUseCase<Result>() {

    override fun execute() {
        repository.fetchFiles()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::success, ::error)
            .track()
    }

    private fun success(files: List<FileResponseDto>) {
        liveData.value = Result.OnSuccess(files)
    }

    private fun error(throwable: Throwable) {
        liveData.value = Result.OnError(throwable.message.toString())
    }
}