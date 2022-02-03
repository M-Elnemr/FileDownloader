package com.nagwa.filedownloader.ui.main.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.nagwa.filedownloader.R
import com.nagwa.filedownloader.base.extension.observe
import com.nagwa.filedownloader.base.network.model.FileResponseDto
import com.nagwa.filedownloader.base.view.BaseActivity
import com.nagwa.filedownloader.ui.main.adapter.movies.MoviesAdapter
import com.nagwa.filedownloader.ui.main.viewmodel.MainViewModel
import com.nagwa.filedownloader.ui.main.viewmodel.MainViewModelFactory
import com.nagwa.filedownloader.utils.Constants
import com.nagwa.filedownloader.utils.crash.CrashReportActivity
import com.nagwa.filedownloader.utils.crash.HandleAppCrash
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity(override val layoutResourceId: Int = R.layout.activity_main) : BaseActivity() {

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    @Inject
    lateinit var viewModel: MainViewModel

    @Inject
    lateinit var moviesAdapter: MoviesAdapter

    lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        HandleAppCrash.deploy(this, CrashReportActivity::class.java)

        preferences = getSharedPreferences(
            Constants.APP_NAME,
            Context.MODE_PRIVATE
        )

        initRecyclerView()

        viewModel.fetchFiles()
    }

    private fun initRecyclerView() {
        rv_movies.layoutManager = LinearLayoutManager(this)
        rv_movies.adapter = moviesAdapter
    }

    override fun setUpViewModelStateObservers() {
        observe(viewModel.getState()) { onStateChanged(it) }
    }

    private fun onStateChanged(state: MainViewModel.State) = when (state) {
        is MainViewModel.State.ShowError -> {
        }
        MainViewModel.State.ShowLoading -> {
        }
        is MainViewModel.State.FileFetched -> showFiles(state.files)
    }

    private fun showFiles(files: List<FileResponseDto>) {
        moviesAdapter.setMovies(files)
    }

}
