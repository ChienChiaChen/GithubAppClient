package com.chiachen.gihubappclient.presentation.main.di

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import com.chiachen.gihubappclient.di.ViewModelKey
import com.chiachen.gihubappclient.di.qualifier.FragmentLifecycle
import com.chiachen.gihubappclient.presentation.main.MainFragment
import com.chiachen.gihubappclient.presentation.main.MainFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [MainFragmentModule.Binding::class])
class MainFragmentModule(private val fragment: MainFragment) {

    @Provides
    @FragmentLifecycle
    fun provideLifecycle(): Lifecycle = fragment.lifecycle

    @Module
    interface Binding {
        @Binds
        @IntoMap
        @ViewModelKey(MainFragmentViewModel::class)
        fun provideViewModel(viewModel: MainFragmentViewModel): ViewModel

    }

}