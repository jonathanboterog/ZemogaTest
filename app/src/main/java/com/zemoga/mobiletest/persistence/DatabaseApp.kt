package com.zemoga.mobiletest.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zemoga.mobiletest.persistence.DatabaseConstants.Companion.DB_VERSION
import com.zemoga.mobiletest.persistence.dao.CommentDao
import com.zemoga.mobiletest.persistence.dao.PostDao
import com.zemoga.mobiletest.persistence.dao.UserDao
import com.zemoga.mobiletest.persistence.entity.CommentEntity
import com.zemoga.mobiletest.persistence.entity.PostEntity
import com.zemoga.mobiletest.persistence.entity.UserEntity

@Database(
    entities = [
        PostEntity::class,
        UserEntity::class,
        CommentEntity::class
    ],
    version = DB_VERSION
)

abstract class DatabaseApp : RoomDatabase() {
    abstract fun postDao() : PostDao
    abstract fun userDao() : UserDao
    abstract fun commentDao() : CommentDao
}
