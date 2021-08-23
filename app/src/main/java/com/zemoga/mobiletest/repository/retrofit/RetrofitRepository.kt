package com.zemoga.mobiletest.repository.retrofit

import android.util.Log
import com.zemoga.mobiletest.network.restapi.RetrofitClient
import com.zemoga.mobiletest.persistence.dao.CommentDao
import com.zemoga.mobiletest.persistence.dao.PostDao
import com.zemoga.mobiletest.persistence.dao.UserDao
import com.zemoga.mobiletest.persistence.entity.CommentEntity
import com.zemoga.mobiletest.persistence.entity.PostEntity
import com.zemoga.mobiletest.persistence.entity.UserEntity
import com.zemoga.mobiletest.util.Resource
import javax.inject.Inject

class RetrofitRepository @Inject constructor(
    private val postDao: PostDao,
    private val userDao: UserDao,
    private val commentDao: CommentDao) : IRetrofitRepository {

    companion object{
        private val TAG = RetrofitRepository::class.java.simpleName
    }

    override suspend fun getAll(): Boolean {
        return if(postDao.getNumRows() <= 0){
            val resultPost = getPosts()
            val resultUser = getUsers()
            val resultComment = getComments()

            resultPost is Resource.Success &&
                    resultUser is Resource.Success &&
                    resultComment is Resource.Success
        } else {
            true
        }
    }

    override suspend fun getPosts(): Resource<MutableList<PostEntity>> {
        try {
            val result = RetrofitClient.apiService.getPosts()
            Log.d(TAG, "getPosts result = $result")

            return if (result.isSuccessful) {
                val postList = result.body()
                if (postList != null) {
                    postDao.deleteAll()
                    for (post in postList) {
                        postDao.insert(
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
                    Resource.Success(postDao.getAll())
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

    override suspend fun getUsers(): Resource<MutableList<UserEntity>> {
        try {
            val result = RetrofitClient.apiService.getUsers()
            Log.d(TAG, "getUsers result = $result")

            return if (result.isSuccessful) {
                val userList = result.body()
                if (userList != null) {
                    userDao.deleteAll()
                    for (user in userList) {
                        userDao.insert(
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
                    Resource.Success(userDao.getAll())
                } else {
                    Log.d(TAG, "UserList apiService result body is null")
                    Resource.Failure(Exception("body is null"))
                }
            } else {
                Log.d(TAG, "UserList apiService result no successful")
                Resource.Failure(Exception("No successful request"))
            }
        } catch (e: Exception) {
            Log.d(TAG, "UserList apiService exception: $e")
            return Resource.Failure(e)
        }
    }

    override suspend fun getComments(): Resource<MutableList<CommentEntity>> {
        try {
            val result = RetrofitClient.apiService.getComments()
            Log.d(TAG, "getComments result = $result")

            return if (result.isSuccessful) {
                val commentList = result.body()
                if (commentList != null) {
                    commentDao.deleteAll()
                    for (comment in commentList) {
                        commentDao.insert(
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
                    Resource.Success(commentDao.getAll())
                } else {
                    Log.d(TAG, "CommentList apiService result body is null")
                    Resource.Failure(Exception("body is null"))
                }
            } else {
                Log.d(TAG, "CommentList apiService result no successful")
                Resource.Failure(Exception("No successful request"))
            }
        } catch (e: Exception) {
            Log.d(TAG, "CommentList apiService exception: $e")
            return Resource.Failure(e)
        }
    }
}