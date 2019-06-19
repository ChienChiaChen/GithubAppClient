package com.chiachen.gihubappclient.presentation.main.di

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.chiachen.gihubappclient.util.rx.ISchedulersProvider
import com.chiachen.gihubappclient.api.github.GithubService
import com.chiachen.gihubappclient.di.scope.PerFragment
import com.chiachen.gihubappclient.model.Item
import com.chiachen.gihubappclient.model.RepoSearchResponse
import com.chiachen.gihubappclient.util.extension.asLiveData
import dagger.Module
import dagger.Provides
import io.reactivex.Single

@Module
class MainFragmentViewModelModule {

    @Provides
    @PerFragment
    internal fun provideQueryLiveData(): MutableLiveData<String> = MutableLiveData()

    @Provides
    @PerFragment
    internal fun providePageNum(): MutableLiveData<Int> = MutableLiveData()

    @Provides
    @PerFragment
    internal fun provideSearchData(
        queryLiveData: MutableLiveData<String>,
        pageNum: MutableLiveData<Int>,
        githubService: GithubService,
        schedulers: ISchedulersProvider
    ): LiveData<List<Item>> =

        Transformations.switchMap(queryLiveData) { input ->
                githubService.searchRepos(input, 1)
                    .subscribeOn(schedulers.io())
                    .observeOn(schedulers.ui())
                    .onErrorReturn {
                        pageNum.value = 0
                        return@onErrorReturn RepoSearchResponse()
                    }
                    .flatMap {
                        pageNum.value?.plus(1)
                        return@flatMap Single.just(it.items)
                    }
                    .asLiveData()
        }

}
