package com.example.domain.repository

import com.example.domain.entity.UserEntity

interface UserRepository {

    suspend fun saveUser(user: UserEntity)

    suspend fun getUsers(): List<UserEntity>
}