package com.zemoga.mobiletest.network.restapi

import com.zemoga.mobiletest.network.restapi.model.CommentList
import com.zemoga.mobiletest.network.restapi.model.PostList
import com.zemoga.mobiletest.network.restapi.model.UserList
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): Response<PostList>

    @GET("users")
    suspend fun getUsers(): Response<UserList>

    @GET("comments")
    suspend fun getComments(): Response<CommentList>
}