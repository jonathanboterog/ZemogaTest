package com.zemoga.mobiletest.repository

import com.zemoga.mobiletest.network.restapi.Resource

interface IRepository {
    suspend fun  getPostList() : Resource
}