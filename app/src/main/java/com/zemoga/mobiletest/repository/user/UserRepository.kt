package com.zemoga.mobiletest.repository.user

import android.util.Log
import com.zemoga.mobiletest.persistence.dao.UserDao
import com.zemoga.mobiletest.persistence.entity.UserEntity
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao,
) : IUserRepository {

    companion object{
        private val TAG = UserRepository::class.java.simpleName
    }

    override suspend fun getUserById(userId: Int): UserEntity {
        return userDao.getById(userId).also{
            Log.d(TAG, "getUserById = $it")
        }
    }

    override suspend fun deleteAll() {
        userDao.deleteAll()
    }

}