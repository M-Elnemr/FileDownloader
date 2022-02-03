package com.nagwa.filedownloader.ui.main.adapter.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.nagwa.filedownloader.R
import com.nagwa.filedownloader.base.network.model.FileResponseDto
import com.nagwa.filedownloader.base.view.adapter.DiffCallback
import javax.inject.Inject

class MoviesAdapterImpl @Inject constructor(
    private val diffCallback: DiffCallback
) : MoviesAdapter() {

    private var movies: MutableList<FileResponseDto> = mutableListOf()

    override fun getItemViewType(position: Int) = R.layout.view_holder_movie

    override fun getItemCount() = movies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_movie, parent, false)
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun setMovies(movies: List<FileResponseDto>) {
        calculateDiff(ArrayList(movies))
    }

    override fun addMovies(movies: List<FileResponseDto>) {
        val list = ArrayList(this.movies)
        list.addAll(movies)
        calculateDiff(list)
    }

    override fun clearMovies() {
        calculateDiff(emptyList())
    }

    private fun calculateDiff(movies: List<FileResponseDto>) {
        diffCallback.setLists(this.movies, movies)
        val result = DiffUtil.calculateDiff(diffCallback)
        this.movies.clear()
        this.movies.addAll(movies)
        result.dispatchUpdatesTo(this)
    }
}
