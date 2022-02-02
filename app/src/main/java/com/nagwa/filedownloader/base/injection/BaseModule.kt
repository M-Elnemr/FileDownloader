package com.nagwa.filedownloader.base.injection

import android.app.Application
import android.content.Context
import com.nagwa.filedownloader.base.injection.qualifiers.ForApplication
import com.nagwa.filedownloader.base.injection.scopes.PerApplication
import com.nagwa.filedownloader.base.view.adapter.DiffCallback
import dagger.Module
import dagger.Provides

@Module
class BaseModule {

    @Provides
    @PerApplication
    @ForApplication
    fun provideContext(application: Application): Context = application

    @Provides
    @PerApplication
    fun provideDiffCallback(): DiffCallback = DiffCallback()
}
