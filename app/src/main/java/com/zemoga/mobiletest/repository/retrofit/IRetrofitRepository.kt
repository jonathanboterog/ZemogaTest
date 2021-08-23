package com.zemoga.mobiletest.repository.retrofit

import com.zemoga.mobiletest.persistence.entity.CommentEntity
import com.zemoga.mobiletest.persistence.entity.PostEntity
import com.zemoga.mobiletest.persistence.entity.UserEntity
import com.zemoga.mobiletest.util.Resource

interface IRetrofitRepository {
    suspend fun getAll() : Boolean
    suspend fun getPosts() : Resource<MutableList<PostEntity>>
    suspend fun getUsers() : Resource<MutableList<UserEntity>>
    suspend fun getComments() : Resource<MutableList<CommentEntity>>
}