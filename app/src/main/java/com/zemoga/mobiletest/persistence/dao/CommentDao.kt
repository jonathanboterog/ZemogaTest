package com.zemoga.mobiletest.persistence.dao

import androidx.room.*
import com.zemoga.mobiletest.persistence.entity.CommentEntity

@Dao
interface CommentDao {

    @Query("SELECT COUNT() FROM comment")
    fun getNumRows(): Int

    @Query("SELECT * FROM comment")
    fun getAll(): MutableList<CommentEntity>

    @Query("SELECT * FROM comment WHERE id = :id")
    fun getById(id : Int): CommentEntity

    @Query("SELECT * FROM comment WHERE postId = :id")
    fun getByPostId(id : Int): MutableList<CommentEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(commentEntity: CommentEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArray(arrCommentEntity: MutableList<CommentEntity>)

    @Delete
    fun delete(commentEntity: CommentEntity)

    @Query("DELETE FROM comment")
    fun deleteAll()
}