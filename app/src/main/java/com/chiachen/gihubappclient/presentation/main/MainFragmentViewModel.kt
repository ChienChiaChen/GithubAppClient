package com.chiachen.gihubappclient.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chiachen.gihubappclient.model.Item
import javax.inject.Inject

class MainFragmentViewModel @Inject constructor(
    private val queryText: MutableLiveData<String>,
    val pageNum: MutableLiveData<Int>,
    val dataSet: LiveData<List<Item>>
) : ViewModel() {
    init {
        pageNum.value = 1
    }

    fun setNewQuery(newQuery: String) {
//        pageNum.value = 1
        queryText.value = newQuery.trim()
    }

    fun loadMore() {
        pageNum.value?.plus(1)
    }
}