package com.zemoga.mobiletest.repository.post

import android.util.Log
import com.zemoga.mobiletest.persistence.dao.PostDao
import com.zemoga.mobiletest.persistence.entity.PostEntity
import com.zemoga.mobiletest.util.Resource

class PostRepository constructor(
    private val postDao: PostDao
) : IPostRepository{

    companion object{
        private val TAG = PostRepository::class.java.simpleName
    }

    override suspend fun getPosts(): Resource<MutableList<PostEntity>> {
        return Resource.Success(postDao.getAll())
    }

    override suspend fun getPostById(postId : Int): PostEntity {
        return postDao.getById(postId).also{
            Log.d(TAG, "getPostById = $it")
        }
    }

    override suspend fun setRead(postId: Int) {
        postDao.updateRead(true, postId)
    }

    override suspend fun setFavorite(postId: Int): Boolean {
        val postEntity = postDao.getById(postId)
        postDao.updateFavorite(!postEntity.favorite, postId)
        return !postEntity.favorite
    }

    override suspend fun getFavoritesPost() : Resource<MutableList<PostEntity>> {
        return Resource.Success(postDao.getAllFavorites()).also{
            Log.d(TAG, "getFavoritesPost = $it")
        }
    }

    override suspend fun deletePost(postEntity: PostEntity){
        postDao.delete(postEntity)
    }

    override suspend fun deleteAll() {
        postDao.deleteAll()
    }
}