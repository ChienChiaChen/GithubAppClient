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
    private var flagOfUserName = ""

    val dataSet: LiveData<RepoSearchResponse> = Transformations.switchMap(queryLiveData) { input ->
        if (input.first.isEmpty()) {
            flagOfUserName = ""
        }
        githubService.searchRepos(input.first, input.second)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .onErrorReturn {
                return@onErrorReturn RepoSearchResponse().apply {
                    isSame = input.first.isNotEmpty() && input.first == flagOfUserName
                    flagOfUserName = ""
                    page = 1
                }
            }
            .doOnSuccess {
                flagOfUserName = input.first
                it.isSame = it.items.isEmpty() && flagOfUserName.isNotEmpty()
                page++
            }
            .asLiveData()
    }


    fun setNewQuery(newQuery: String) {
        page = 1
        queryLiveData.value = Pair(newQuery.trim(), page)
    }

    fun loadMore() {
        queryLiveData.value = Pair(flagOfUserName, page)
    }
}