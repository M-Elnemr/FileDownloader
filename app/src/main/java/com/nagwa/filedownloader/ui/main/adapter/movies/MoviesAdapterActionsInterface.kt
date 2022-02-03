package com.nagwa.filedownloader.ui.main.adapter.movies

import com.nagwa.filedownloader.base.network.model.FileResponseDto

interface MoviesAdapterActionsInterface {
    fun onDownloadClicked(data: FileResponseDto)
}