package com.chiachen.gihubappclient.presentation.main.di.fragment

import com.chiachen.gihubappclient.di.scope.PerFragment
import com.chiachen.gihubappclient.presentation.main.MainFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [MainFragmentModule::class])
@PerFragment
interface MainFragmentSubComponent : AndroidInjector<MainFragment> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainFragment>() {

        abstract fun module(module: MainFragmentModule): Builder

        override fun seedInstance(instance: MainFragment) {
            module(MainFragmentModule(instance))
        }
    }

}