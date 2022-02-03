package com.nagwa.filedownloader.app.injection

import com.nagwa.filedownloader.base.injection.scopes.PerActivity
import com.nagwa.filedownloader.ui.main.injection.MainModule
import com.nagwa.filedownloader.ui.main.view.MainActivity
import com.nagwa.filedownloader.utils.crash.CrashReportActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class Bindings {

    @PerActivity
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun bindMainActivity(): MainActivity

    @PerActivity
    @ContributesAndroidInjector()
    abstract fun bindCrashActivity(): CrashReportActivity

}
