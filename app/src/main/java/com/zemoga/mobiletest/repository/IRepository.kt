package com.zemoga.mobiletest.repository

import com.zemoga.mobiletest.network.restapi.Resource
import com.zemoga.mobiletest.persistence.entity.CommentEntity
import com.zemoga.mobiletest.persistence.entity.PostEntity
import com.zemoga.mobiletest.persistence.entity.UserEntity
import com.zemoga.mobiletest.ui.model.DescriptionModel

interface IRepository {
    suspend fun requestServiceApi() : Boolean
    suspend fun getPostListServiceApi(): Resource<MutableList<PostEntity>>
    suspend fun getUserListServiceApi(): Resource<MutableList<UserEntity>>
    suspend fun getCommentListServiceApi(): Resource<MutableList<CommentEntity>>

    suspend fun getPostList(): Resource<MutableList<PostEntity>>
    suspend fun getPostDescription(postId : Int): DescriptionModel
    suspend fun deletePost(postEntity: PostEntity)
    suspend fun deleteAllPost()
    suspend fun setRead(postId: Int)
    suspend fun setFavorite(postId: Int) : Boolean
    suspend fun getFavoritesPost(): Resource<MutableList<PostEntity>>
}