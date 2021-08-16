package com.zemoga.mobiletest.persistence.dao

import androidx.room.*
import com.zemoga.mobiletest.persistence.entity.PostEntity

@Dao
interface PostDao {

    @Query("SELECT COUNT() FROM post")
    fun getNumRows(): Int

    @Query("SELECT * FROM post ORDER BY read ASC")
    fun getAll(): MutableList<PostEntity>

    @Query("SELECT * FROM post WHERE favorites = 1")
    fun getAllFavorites(): MutableList<PostEntity>

    @Query("SELECT * FROM post WHERE id = :id")
    fun getById(id : Int): PostEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(postEntity: PostEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArray(arrPostEntity: MutableList<PostEntity>)

    @Query("UPDATE post SET read = :read WHERE id = :postId")
    fun updateRead(read : Boolean, postId : Int)

    @Query("UPDATE post SET favorites = :favorite WHERE id = :postId")
    fun updateFavorite(favorite : Boolean, postId : Int)

    @Delete
    fun delete(postEntity: PostEntity)

    @Query("DELETE FROM post")
    fun deleteAll()
}