package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.datasource.UserOfflineDataSource
import com.example.data.datasource.UserOfflineDataSourceImpl
import com.example.data.local.dao.UserDao
import com.example.data.local.database.AppDatabase
import com.example.data.repository.UserRepositoryImpl
import com.example.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataBindModule {

    @Binds
    abstract fun bindUserRepository(
        impl: UserRepositoryImpl
    ): UserRepository

    @Binds
    abstract fun bindOfflineDataSource(
        impl: UserOfflineDataSourceImpl
    ): UserOfflineDataSource
}

@Module
@InstallIn(SingletonComponent::class)
object DataProvideModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "user_db"
        ).build()

    @Provides
    fun provideUserDao(
        db: AppDatabase
    ): UserDao = db.userDao()


}