package com.nagwa.filedownloader.ui.main.adapter.movies

import android.view.View
import androidx.core.view.isVisible
import com.nagwa.filedownloader.base.network.model.FileResponseDto
import com.nagwa.filedownloader.base.view.adapter.BaseViewHolder
import kotlinx.android.synthetic.main.view_holder_movie.view.*

class MovieViewHolder(
    itemView: View,
) : BaseViewHolder<FileResponseDto>(itemView) {

    override fun bind(data: FileResponseDto) {

        itemView.tvMovieName.text = data.name
        itemView.tvMovieType.text = data.type

        setViewClickListener(data)
    }

    private fun setViewClickListener(
        data: FileResponseDto
    ) {

    }

    fun toggleStatus(download: Boolean, downloaded: Boolean, progress: Boolean) {
        itemView.iv_download.isVisible = download
        itemView.iv_downloaded.isVisible = downloaded
        itemView.linear_progress.isVisible = progress
    }
}
