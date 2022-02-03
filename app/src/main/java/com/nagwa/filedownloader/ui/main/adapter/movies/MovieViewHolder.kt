package com.nagwa.filedownloader.ui.main.adapter.movies

import android.os.Environment
import android.util.Log
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

//        val file = File(Environment.DIRECTORY_MOVIES,"/nagwa//new.jpg" )
//        if (file.exists()) Log.d("TAG", "bind: exists")
//        else file.createNewFile()

        val file2 = File(Environment.DIRECTORY_PICTURES,"/YourDirectory//ford.jpg" )
        if (file2.exists()) Log.d("TAG", "bind: exist") else Log.d("TAG", "bind: not exist")



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
