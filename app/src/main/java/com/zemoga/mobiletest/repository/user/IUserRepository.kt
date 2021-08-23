package com.zemoga.mobiletest.repository.user

import com.zemoga.mobiletest.persistence.entity.UserEntity

interface IUserRepository {
    suspend fun getUserById(userId: Int): UserEntity
    suspend fun deleteAll()
}