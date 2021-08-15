package com.zemoga.mobiletest.persistence.dao

import androidx.room.*
import com.zemoga.mobiletest.persistence.entity.UserEntity

@Dao
interface UserDao {

    @Query("SELECT COUNT() FROM user")
    fun getNumRows(): Int

    @Query("SELECT * FROM user")
    fun getAll(): MutableList<UserEntity>

    @Query("SELECT * FROM user WHERE id = :id")
    fun getById(id : Int): UserEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userEntity: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArray(arrUserEntity: MutableList<UserEntity>)

    @Delete
    fun delete(userEntity: UserEntity)

    @Query("DELETE FROM user")
    fun deleteAll()
}