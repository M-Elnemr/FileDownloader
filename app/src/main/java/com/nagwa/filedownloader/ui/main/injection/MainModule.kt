package com.nagwa.filedownloader.ui.main.injection

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.nagwa.filedownloader.base.injection.qualifiers.ForActivity
import com.nagwa.filedownloader.base.injection.scopes.PerActivity
import com.nagwa.filedownloader.ui.main.adapter.movies.MoviesAdapter
import com.nagwa.filedownloader.ui.main.adapter.movies.MoviesAdapterImpl
import com.nagwa.filedownloader.ui.main.domain.FetchFilesUseCase
import com.nagwa.filedownloader.ui.main.domain.FetchFilesUseCaseImpl
import com.nagwa.filedownloader.ui.main.repository.MainRepository
import com.nagwa.filedownloader.ui.main.repository.MainRepositoryImpl
import com.nagwa.filedownloader.ui.main.view.MainActivity
import com.nagwa.filedownloader.ui.main.viewmodel.MainViewModel
import com.nagwa.filedownloader.ui.main.viewmodel.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        MainModule.View::class,
        MainModule.ViewModel::class,
        MainModule.Repository::class,
        MainModule.Adapter::class,
        MainModule.UseCase::class
    ]
)
class MainModule {

    @Module
    class ViewModel {
        @Provides
        @PerActivity
        fun provideMainViewModel(
            activity: MainActivity,
            factory: MainViewModelFactory
        ): MainViewModel =
            ViewModelProvider(activity, factory).get(MainViewModel::class.java)
    }

    @Module
    class Adapter {
        @Provides
        @PerActivity
        fun provideMoviesAdapter(
            moviesAdapterImpl: MoviesAdapterImpl
        ): MoviesAdapter = moviesAdapterImpl
    }

    @Module
    class Repository {
        @Provides
        @PerActivity
        fun provideRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository =
            mainRepositoryImpl
    }

    @Module
    class UseCase {
        @Provides
        @PerActivity
        fun provideFetchFilesUseCase(fetchFilesUseCaseImpl: FetchFilesUseCaseImpl): FetchFilesUseCase =
            fetchFilesUseCaseImpl
    }

    @Module
    class View {
        @Provides
        @PerActivity
        @ForActivity
        fun provideContext(activity: MainActivity): Context = activity
    }
}
