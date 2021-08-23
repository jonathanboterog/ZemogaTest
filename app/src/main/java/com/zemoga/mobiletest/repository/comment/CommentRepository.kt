package com.zemoga.mobiletest.repository.comment

import android.util.Log
import com.zemoga.mobiletest.persistence.dao.CommentDao
import com.zemoga.mobiletest.persistence.entity.CommentEntity
import javax.inject.Inject

class CommentRepository @Inject constructor(
    private val commentDao: CommentDao
) : ICommentRepository {

    companion object{
        private val TAG = CommentRepository::class.java.simpleName
    }

    override suspend fun getCommentsByPostId(postId : Int): MutableList<CommentEntity> {
        return commentDao.getByPostId(postId).also{
            Log.d(TAG, "getCommentsByPostId = $it")
        }
    }

    override suspend fun deleteAll() {
        commentDao.deleteAll()
    }
}