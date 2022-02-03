package com.nagwa.filedownloader.ui.main.repository

import com.nagwa.filedownloader.base.network.api.ApiInterface
import com.nagwa.filedownloader.base.network.model.FileResponseDto
import io.reactivex.Single
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val api: ApiInterface) : MainRepository {

    override fun fetchFiles(): Single<List<FileResponseDto>> = api.fetchFiles()

}
