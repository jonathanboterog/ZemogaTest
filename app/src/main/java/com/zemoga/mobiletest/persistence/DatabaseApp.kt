package com.zemoga.mobiletest.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zemoga.mobiletest.persistence.DatabaseConstants.Companion.DB_VERSION
import com.zemoga.mobiletest.persistence.dao.PostDao
import com.zemoga.mobiletest.persistence.entity.PostEntity

@Database(
    entities = [
        PostEntity::class,
    ],
    version = DB_VERSION
)

abstract class DatabaseApp : RoomDatabase() {
    abstract fun postDao() : PostDao
}
