package com.chiachen.gihubappclient.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainFragmentViewModel @Inject constructor(
    private val queryText: MutableLiveData<String>
) : ViewModel() {
    fun setNewQuery(newQuery: String) {
        queryText.value = newQuery.trim()
    }
}