package com.chiachen.gihubappclient.presentation.main.di

import com.chiachen.gihubappclient.presentation.main.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(subcomponents = [MainActivitySubComponent::class])
abstract class MainActivityInjector {

    @Binds
    @IntoMap
    @ClassKey(MainActivity::class)
    internal abstract fun injectorFactory(builder: MainActivitySubComponent.Builder)
            : AndroidInjector.Factory<*>

}