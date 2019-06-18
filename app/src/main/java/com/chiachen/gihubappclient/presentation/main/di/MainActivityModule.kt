package com.chiachen.gihubappclient.presentation.main.di

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.chiachen.gihubappclient.di.qualifier.ActivityContext
import com.chiachen.gihubappclient.di.qualifier.ActivityLifecycle
import com.chiachen.gihubappclient.di.scope.PerActivity
import com.chiachen.gihubappclient.presentation.main.MainActivity
import com.chiachen.gihubappclient.presentation.navigator.Navigator
import com.chiachen.gihubappclient.presentation.navigator.NavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [MainActivityModule.Binding::class])
class MainActivityModule(private val activity: MainActivity) {

    @Provides
    fun provideInstance() = activity


    @Provides
    @ActivityContext
    internal fun provideContext(): Context = activity

    @Provides
    @ActivityLifecycle
    internal fun provideLifecycle(): Lifecycle = activity.lifecycle

    @Provides
    internal fun provideLifecycleOwner(): LifecycleOwner = activity

    @Provides
    internal fun provideActivity(): Activity = activity

    @Provides
    internal fun provideSupportActivity(): AppCompatActivity = activity

    @Provides
    internal fun provideFragmentActivity(): androidx.fragment.app.FragmentActivity = activity

    @Module
    interface Binding {

        @Binds
        @PerActivity
        fun provideNavigator(navigatorImpl: NavigatorImpl): Navigator
    }

}