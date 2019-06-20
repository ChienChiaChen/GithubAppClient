package com.chiachen.githubappclient.di

import com.chiachen.githubappclient.App
import com.chiachen.githubappclient.api.github.GithubModule
import com.chiachen.githubappclient.presentation.main.di.activity.MainActivityInjector
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