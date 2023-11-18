package com.example.mygithubdb.data.retrofit

import com.example.mygithubdb.model.GithubResponse
import com.example.mygithubdb.model.GithubUserDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    fun getUser(
        @Query("q") login : String
    ) : Call<GithubResponse>

    @GET("users/{login}")
    fun getDetailUser(
        @Path("login") login : String
    ) : Call<GithubUserDetailResponse>
}