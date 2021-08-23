package com.zemoga.mobiletest.repository.post

import com.zemoga.mobiletest.persistence.entity.PostEntity
import com.zemoga.mobiletest.util.Resource

interface IPostRepository {
    suspend fun getPosts(): Resource<MutableList<PostEntity>>
    suspend fun getPostById(postId : Int): PostEntity
    suspend fun setRead(postId: Int)
    suspend fun setFavorite(postId: Int) : Boolean
    suspend fun getFavoritesPost() : Resource<MutableList<PostEntity>>
    suspend fun deletePost(postEntity: PostEntity)
    suspend fun deleteAll()
}