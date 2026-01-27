package com.example.domain.usecase

import com.example.domain.entity.UserEntity
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(
    private val repository: UserRepository
) {

    suspend operator fun invoke(user: UserEntity) {
        repository.saveUser(user)
    }

}