package com.nagwa.filedownloader.base.db

import com.nagwa.filedownloader.base.injection.scopes.PerApplication
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    @PerApplication
    fun provideDataDao(mainDatabase: MainDatabase): DataDao =
        mainDatabase.dataDao()
}