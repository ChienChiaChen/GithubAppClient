package com.chiachen.gihubappclient.di

import com.chiachen.gihubappclient.App
import com.chiachen.gihubappclient.api.github.GithubModule
import com.chiachen.gihubappclient.presentation.main.di.activity.MainActivityInjector
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        MainActivityInjector::class,
        ViewModelModule::class,
        GithubModule::class
    ]
)
@Singleton
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>() {

        internal abstract fun module(module: AppModule): Builder

        override fun seedInstance(instance: App) {
            module(AppModule(instance))
        }
    }

}