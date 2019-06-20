package com.chiachen.gihubappclient.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.chiachen.gihubappclient.api.github.GithubService
import com.chiachen.gihubappclient.model.RepoSearchResponse
import com.chiachen.gihubappclient.util.extension.asLiveData
import com.chiachen.gihubappclient.util.rx.ISchedulersProvider
import javax.inject.Inject

class MainFragmentViewModel @Inject constructor(
    private val githubService: GithubService,
    private val schedulers: ISchedulersProvider
) : ViewModel() {

    private var queryLiveData: MutableLiveData<Pair<String, Int>> = MutableLiveData()
    private var page: Int = 1
    private var currentQueryName = ""

    val dataSet: LiveData<RepoSearchResponse> = Transformations.switchMap(queryLiveData) { input ->
        if (input.first.isEmpty()) {
            currentQueryName = ""
        }
        githubService.searchRepos(input.first, input.second)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .onErrorReturn {
                return@onErrorReturn RepoSearchResponse().apply {
                    isSame = input.first.isNotEmpty() && input.first == currentQueryName
                    currentQueryName = ""
                    page = 1
                }
            }
            .doOnSuccess {
                currentQueryName = input.first
                it.isSame = it.items.isEmpty() && currentQueryName.isNotEmpty()
                page++
            }
            .asLiveData()
    }


    fun setNewQuery(newQuery: String) {
        page = 1
        queryLiveData.value = Pair(newQuery.trim(), page)
    }

    fun loadMore() {
        queryLiveData.value = Pair(currentQueryName, page)
    }
}