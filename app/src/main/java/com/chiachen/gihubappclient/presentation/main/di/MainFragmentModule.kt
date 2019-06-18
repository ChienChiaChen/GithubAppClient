package com.chiachen.gihubappclient.presentation.main.di

import androidx.lifecycle.Lifecycle
import com.chiachen.gihubappclient.di.qualifier.FragmentLifecycle
import com.chiachen.gihubappclient.presentation.main.MainFragment
import dagger.Module
import dagger.Provides

@Module(includes = [MainFragmentModule.Binding::class])
class MainFragmentModule(private val fragment: MainFragment) {

    @Provides
    @FragmentLifecycle
    fun provideLifecycle(): Lifecycle = fragment.lifecycle

    @Module
    interface Binding {

    }

}