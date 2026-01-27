package com.example.data.repository

import com.example.data.datasource.UserOfflineDataSource
import com.example.data.mapper.UserMapper
import com.example.domain.entity.UserEntity
import com.example.domain.repository.UserRepository
import javax.inject.Inject


class UserRepositoryImpl @Inject constructor(
    private val offlineDataSource: UserOfflineDataSource
) : UserRepository {

    override suspend fun saveUser(user: UserEntity) {

        val data = UserMapper.toRoom(user);

        offlineDataSource.saveUser( data )
    }

    override suspend fun getUsers(): List<UserEntity> {
        return offlineDataSource.getUsers()
            .map { UserMapper.toDomain(it) }
    }
}