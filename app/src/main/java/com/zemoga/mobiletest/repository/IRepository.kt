package com.zemoga.mobiletest.repository

import com.zemoga.mobiletest.network.restapi.Resource
import com.zemoga.mobiletest.ui.model.DescriptionModel

interface IRepository {
    suspend fun getPostListServiceApi(): Resource
    suspend fun getUserListServiceApi()
    suspend fun getCommentListServiceApi()

    suspend fun getPostList() : Resource
    suspend fun getPostDescription(postId : Int): DescriptionModel
    suspend fun deleteDatabaseRegister()
    suspend fun setRead(postId: Int)
    suspend fun setFavorite(postId: Int) : Boolean
}