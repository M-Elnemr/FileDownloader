package com.nagwa.filedownloader.ui.main.adapter.movies

import androidx.recyclerview.widget.RecyclerView
import com.nagwa.filedownloader.base.network.model.FileResponseDto

abstract class MoviesAdapter : RecyclerView.Adapter<MovieViewHolder>() {

    abstract fun setMovies(movies: List<FileResponseDto>)

    abstract fun addMovies(movies: List<FileResponseDto>)

    abstract fun clearMovies()
}
