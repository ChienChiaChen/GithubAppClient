package com.chiachen.gihubappclient.api.github

import com.chiachen.gihubappclient.model.RepoSearchResponse

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface GithubService {

    @GET("search/users")
    fun searchRepos(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 10
    ): Single<RepoSearchResponse>
}