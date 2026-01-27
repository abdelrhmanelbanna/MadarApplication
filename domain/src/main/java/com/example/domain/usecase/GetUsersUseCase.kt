package com.example.domain.usecase

import com.example.domain.entity.UserEntity
import com.example.domain.repository.UserRepository
import javax.inject.Inject


class GetUsersUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(): List<UserEntity> {
        return repository.getUsers()
    }
}