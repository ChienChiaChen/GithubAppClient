package com.chiachen.githubappclient.presentation.main.di.activity

import com.chiachen.githubappclient.di.scope.PerActivity
import com.chiachen.githubappclient.presentation.main.MainActivity
import com.chiachen.githubappclient.presentation.main.di.fragment.MainFragmentInjector
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [
    MainActivityModule::class,
    MainFragmentInjector::class
])
@PerActivity
interface MainActivitySubComponent : AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>() {

        abstract fun module(module: MainActivityModule): Builder

        override fun seedInstance(instance: MainActivity) {
            module(MainActivityModule(instance))
        }
    }

}