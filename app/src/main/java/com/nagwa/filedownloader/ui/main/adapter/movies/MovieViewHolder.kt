package com.nagwa.filedownloader.ui.main.adapter.movies

import android.os.Environment
import android.view.View
import androidx.core.view.isVisible
import com.nagwa.filedownloader.base.network.model.FileResponseDto
import com.nagwa.filedownloader.base.view.adapter.BaseViewHolder
import kotlinx.android.synthetic.main.view_holder_movie.view.*
import java.io.File

class MovieViewHolder(
    itemView: View,
) : BaseViewHolder<FileResponseDto>(itemView) {

    override fun bind(data: FileResponseDto) {

        data.name?.let { itemView.tvMovieName.text = it }
        data.type?.let { itemView.tvMovieType.text = it }

        val extension = when (data.type) {
            "PDF" -> "pdf"
            "VIDEO" -> "mp4"
            else -> "jpg"
        }

        val file = File(
            Environment.getExternalStorageDirectory().absolutePath,
            "Movies/Nagwa/${data.name}.$extension"
        )
        if (file.exists()) toggleStatus(downloaded = true)
        else toggleStatus(download = true)

    }

    private fun toggleStatus(
        download: Boolean = false,
        downloaded: Boolean = false,
        progress: Boolean = false
    ) {
        itemView.iv_download.isVisible = download
        itemView.iv_downloaded.isVisible = downloaded
        itemView.linear_progress.isVisible = progress
    }
}
