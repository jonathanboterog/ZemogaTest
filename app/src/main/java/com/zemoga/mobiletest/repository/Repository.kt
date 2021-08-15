package com.zemoga.mobiletest.repository

import android.util.Log
import com.zemoga.mobiletest.network.restapi.Resource
import com.zemoga.mobiletest.network.restapi.RetrofitClient
import com.zemoga.mobiletest.persistence.DatabaseApp
import com.zemoga.mobiletest.persistence.entity.PostEntity
import java.lang.Exception
import javax.inject.Inject

class Repository @Inject constructor(private val databaseApp: DatabaseApp) : IRepository {

    companion object{
        private val TAG = Repository::class.java.simpleName
    }

    override suspend fun getPostListServiceApi(): Resource {
        try {
            val result = RetrofitClient.apiService.getPosts()

            return if (result.isSuccessful) {
                val postList = result.body()
                if (postList != null) {
                    for (post in postList) {
                        databaseApp.postDao().insert(
                            PostEntity(
                                title = post.title,
                                body = post.body,
                                userId = post.userId,
                                favorite = false
                            )
                        )
                    }
                    Log.d(TAG, "apiService OK")
                    Resource.Success(databaseApp.postDao().getAll())
                } else {
                    Log.d(TAG, "apiService result body is null")
                    Resource.Failure(Exception("body is null"))
                }
            } else {
                Log.d(TAG, "apiService result no successful")
                Resource.Failure(Exception("No successful request"))
            }

        } catch (e: Exception) {
            Log.d(TAG, "apiService exception: $e")
            return Resource.Failure(e)
        }
    }

    override suspend fun getPostList(): Resource {
        return if(databaseApp.postDao().getNumRows() == 0){
            getPostListServiceApi()
        } else {
            Log.d(TAG, "Database query")
            Resource.Success(databaseApp.postDao().getAll())
        }
    }
}