package com.nagwa.filedownloader.app.injection

import com.nagwa.filedownloader.MainActivity
import com.nagwa.filedownloader.base.injection.scopes.PerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class Bindings {

    @PerActivity
    @ContributesAndroidInjector()
    abstract fun bindMainActivity(): MainActivity

}
