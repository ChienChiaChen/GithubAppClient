package com.chiachen.gihubappclient.presentation.main.di

import com.chiachen.gihubappclient.di.scope.PerActivity
import com.chiachen.gihubappclient.presentation.main.MainActivity
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