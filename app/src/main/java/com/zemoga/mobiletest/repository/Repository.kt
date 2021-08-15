package com.zemoga.mobiletest.repository

import com.zemoga.mobiletest.network.restapi.Resource
import com.zemoga.mobiletest.network.restapi.RetrofitClient
import com.zemoga.mobiletest.persistence.DatabaseApp
import javax.inject.Inject

class Repository @Inject constructor(private val databaseApp: DatabaseApp) : IRepository {

    override suspend fun getPostList(): Resource {
        return Resource.Success(RetrofitClient.apiService.getPosts())
    }

}