package com.chiachen.gihubappclient.model

import com.google.gson.annotations.SerializedName


data class RepoSearchResponse(
    @SerializedName("total_count")
    val total: Int = 0,
    @SerializedName("items")
    val items: List<Item> = arrayListOf()
)
