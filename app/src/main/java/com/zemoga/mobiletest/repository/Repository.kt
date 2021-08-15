package com.zemoga.mobiletest.repository

import android.util.Log
import com.zemoga.mobiletest.network.restapi.Resource
import com.zemoga.mobiletest.network.restapi.RetrofitClient
import com.zemoga.mobiletest.persistence.DatabaseApp
import com.zemoga.mobiletest.persistence.entity.CommentEntity
import com.zemoga.mobiletest.persistence.entity.PostEntity
import com.zemoga.mobiletest.persistence.entity.UserEntity
import com.zemoga.mobiletest.ui.model.DescriptionModel
import javax.inject.Inject

class Repository @Inject constructor(private val databaseApp: DatabaseApp) : IRepository {

    companion object{
        private val TAG = Repository::class.java.simpleName
    }

    override suspend fun getPostListServiceApi(): Resource {
        try {
            val result = RetrofitClient.apiService.getPosts()
            Log.d(TAG, "getPosts result = $result")

            return if (result.isSuccessful) {
                val postList = result.body()
                if (postList != null) {
                    for (post in postList) {
                        databaseApp.postDao().insert(
                            PostEntity(
                                id = post.id,
                                title = post.title,
                                body = post.body,
                                userId = post.userId,
                                favorite = false,
                                read = false
                            )
                        )
                    }
                    Log.d(TAG, "PostList apiService OK")
                    Resource.Success(databaseApp.postDao().getAll())
                } else {
                    Log.d(TAG, "PostList apiService result body is null")
                    Resource.Failure(Exception("body is null"))
                }
            } else {
                Log.d(TAG, "PostList apiService result no successful")
                Resource.Failure(Exception("No successful request"))
            }

        } catch (e: Exception) {
            Log.d(TAG, "PostList apiService exception: $e")
            return Resource.Failure(e)
        }
    }

    override suspend fun getUserListServiceApi(){
        try {
            val result = RetrofitClient.apiService.getUsers()
            Log.d(TAG, "getUsers result = $result")

            if (result.isSuccessful) {
                val userList = result.body()
                if (userList != null) {
                    for (user in userList) {
                        databaseApp.userDao().insert(
                            UserEntity(
                                id = user.id,
                                name = user.name,
                                email = user.email,
                                phone = user.phone,
                                website = user.website
                            )
                        )
                    }
                    Log.d(TAG, "UserList apiService OK")
                } else {
                    Log.d(TAG, "UserList apiService result body is null")
                }
            } else {
                Log.d(TAG, "UserList apiService result no successful")
            }
        } catch (e: Exception) {
            Log.d(TAG, "UserList apiService exception: $e")
        }
    }

    override suspend fun getCommentListServiceApi() {
        try {
            val result = RetrofitClient.apiService.getComments()
            Log.d(TAG, "getComments result = $result")

            if (result.isSuccessful) {
                val commentList = result.body()
                if (commentList != null) {
                    for (comment in commentList) {
                        databaseApp.commentDao().insert(
                            CommentEntity(
                                id = comment.id,
                                postId = comment.postId,
                                body = comment.body,
                                name = comment.name,
                                email = comment.email
                            )
                        )
                    }
                    Log.d(TAG, "CommentList apiService OK")
                } else {
                    Log.d(TAG, "CommentList apiService result body is null")
                }
            } else {
                Log.d(TAG, "CommentList apiService result no successful")
            }
        } catch (e: Exception) {
            Log.d(TAG, "CommentList apiService exception: $e")
        }
    }

    override suspend fun getPostList(): Resource {
        return if(databaseApp.postDao().getNumRows() == 0){
            getUserListServiceApi()
            getCommentListServiceApi()
            getPostListServiceApi()
        } else {

            Resource.Success(databaseApp.postDao().getAll().also{
                Log.d(TAG, "Database query $it")
            })
        }
    }

    override suspend fun getPostDescription(postId : Int) : DescriptionModel {
        val postEntity = databaseApp.postDao().getById(postId)
        val userEntity = databaseApp.userDao().getById(postEntity.userId)
        val commentEntity = databaseApp.commentDao().getByPostId(postId)

        return DescriptionModel(postEntity,
            userEntity,
            commentEntity
        )
    }

    override suspend fun deleteDatabaseRegister() {
        databaseApp.postDao().deleteAll()
        databaseApp.userDao().deleteAll()
        databaseApp.commentDao().deleteAll()
    }

    override suspend fun setRead(postId: Int) {
        databaseApp.postDao().updateRead(true, postId)
    }

    override suspend fun setFavorite(postId: Int) : Boolean{
        val postEntity = databaseApp.postDao().getById(postId)
        databaseApp.postDao().updateFavorite(!postEntity.favorite, postId)
        return !postEntity.favorite
    }
}