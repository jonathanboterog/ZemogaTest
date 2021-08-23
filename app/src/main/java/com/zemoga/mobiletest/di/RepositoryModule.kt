package com.zemoga.mobiletest.di

import com.zemoga.mobiletest.persistence.dao.CommentDao
import com.zemoga.mobiletest.persistence.dao.PostDao
import com.zemoga.mobiletest.persistence.dao.UserDao
import com.zemoga.mobiletest.repository.comment.CommentRepository
import com.zemoga.mobiletest.repository.post.PostRepository
import com.zemoga.mobiletest.repository.user.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providePostRepository(
        postDao: PostDao
    ): PostRepository{
        return PostRepository(postDao)
    }

    @Singleton
    @Provides
    fun provideUserRepository(
        userDao: UserDao
    ): UserRepository{
        return UserRepository(userDao)
    }

    @Singleton
    @Provides
    fun provideCommentRepository(
        commentDao: CommentDao
    ): CommentRepository{
        return CommentRepository(commentDao)
    }
}