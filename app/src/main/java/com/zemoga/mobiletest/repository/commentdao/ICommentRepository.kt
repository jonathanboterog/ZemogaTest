package com.zemoga.mobiletest.repository.commentdao

import com.zemoga.mobiletest.persistence.entity.CommentEntity

interface ICommentRepository {
    suspend fun getCommentsByPostId(postId : Int):  MutableList<CommentEntity>
    suspend fun deleteAll()
}
