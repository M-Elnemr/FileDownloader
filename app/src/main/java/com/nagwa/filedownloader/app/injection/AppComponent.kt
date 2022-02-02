package com.nagwa.filedownloader.app.injection

import com.nagwa.filedownloader.base.db.DatabaseModule
import com.nagwa.filedownloader.base.injection.BaseModule
import com.nagwa.filedownloader.base.injection.scopes.PerApplication
import com.nagwa.filedownloader.base.network.injection.NetworkModule
import com.nagwa.filedownloader.app.MainApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule


@PerApplication
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        Bindings::class,
        AppModule::class,
        BaseModule::class,
        NetworkModule::class,
        DatabaseModule::class
    ]
)
interface AppComponent : AndroidInjector<MainApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MainApp): Builder
        fun build(): AppComponent
    }

}
