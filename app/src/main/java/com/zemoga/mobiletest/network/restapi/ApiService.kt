package com.zemoga.mobiletest.network.restapi

import com.zemoga.mobiletest.network.restapi.model.PostList
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): PostList
}