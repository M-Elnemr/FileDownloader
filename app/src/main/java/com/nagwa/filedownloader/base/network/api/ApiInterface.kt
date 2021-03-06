package com.nagwa.filedownloader.base.network.api

import com.nagwa.filedownloader.base.network.model.FileResponseDto
import io.reactivex.Single
import retrofit2.http.GET

interface ApiInterface {

    @GET("movies")
    fun fetchFiles(): Single<List<FileResponseDto>>

}