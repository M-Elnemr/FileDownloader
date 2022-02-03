package com.nagwa.filedownloader.ui.main.repository

import com.nagwa.filedownloader.base.network.model.FileResponseDto
import io.reactivex.Single

interface MainRepository {

    fun fetchFiles(): Single<List<FileResponseDto>>
}
