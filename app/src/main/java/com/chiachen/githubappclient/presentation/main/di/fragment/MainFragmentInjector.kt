package com.chiachen.githubappclient.presentation.main.di.fragment

import com.chiachen.githubappclient.presentation.main.MainFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap


@Module(subcomponents = [MainFragmentSubComponent::class])
abstract class MainFragmentInjector {

    @Binds
    @IntoMap
    @ClassKey(MainFragment::class)
    internal abstract fun injectorFactory(builder: MainFragmentSubComponent.Builder)
            : AndroidInjector.Factory<*>

}
