package com.nagwa.filedownloader.ui.main.domain

import com.nagwa.filedownloader.base.network.model.FileResponseDto

interface FetchFilesUseCase {
    sealed class Result {
        data class OnSuccess(val files: List<FileResponseDto>) : Result()
        data class OnError(val error: String) : Result()
    }

    fun execute()
}