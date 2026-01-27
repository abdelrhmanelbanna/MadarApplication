package com.example.data.datasource

import com.example.data.model.UserEntityRoom

interface UserOfflineDataSource {


    suspend fun saveUser(user: UserEntityRoom)

    suspend fun getUsers(): List<UserEntityRoom>

}