package com.chiachen.githubappclient.di

import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.net.ConnectivityManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ProcessLifecycleOwner
import com.chiachen.githubappclient.App
import com.chiachen.githubappclient.di.qualifier.ApplicationContext
import com.chiachen.githubappclient.di.qualifier.ProcessLifecycle
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val app: App) {

    @Provides
    @ApplicationContext
    internal fun provideContext() : Context = app

    @Provides
    internal fun provideResources(): Resources = app.resources

    @Provides
    internal fun provideApplication(): Application = app

    @Provides
    @ProcessLifecycle
    internal fun provideAppLifecycle(): Lifecycle {
        return ProcessLifecycleOwner.get().lifecycle
    }

    @Provides
    fun provideConnectivityManager(): ConnectivityManager {
        return app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
}