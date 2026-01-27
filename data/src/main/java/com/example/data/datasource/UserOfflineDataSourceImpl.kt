package com.example.data.datasource

import com.example.data.local.dao.UserDao
import com.example.data.model.UserEntityRoom
import javax.inject.Inject

class UserOfflineDataSourceImpl @Inject constructor(
    private val userDao: UserDao
) : UserOfflineDataSource {

    override suspend fun saveUser(user: UserEntityRoom) {
        userDao.insertUser(user)
    }

    override suspend fun getUsers(): List<UserEntityRoom> {
        return userDao.getUsers()
    }
}