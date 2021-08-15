package com.zemoga.mobiletest.di

import android.content.Context
import androidx.room.Room
import com.zemoga.mobiletest.persistence.DatabaseApp
import com.zemoga.mobiletest.persistence.DatabaseConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRoomInstance(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        DatabaseApp::class.java,
        DatabaseConstants.DB_NAME
    )
        .fallbackToDestructiveMigration()
        .build()
}