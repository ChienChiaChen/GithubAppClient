package com.chiachen.gihubappclient.presentation.main.di

import androidx.lifecycle.MutableLiveData
import com.chiachen.gihubappclient.di.scope.PerFragment
import dagger.Module
import dagger.Provides

@Module
class MainFragmentViewModelModule {

    @Provides
    @PerFragment
    internal fun provideQueryLiveData(): MutableLiveData<String> = MutableLiveData()

}
