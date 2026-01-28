package com.example.domain.usecase

import com.example.domain.entity.UserEntity
import com.example.domain.repository.UserRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class SaveUserUseCaseTest {

    private val repository: UserRepository = mockk()
    private lateinit var saveUserUseCase: SaveUserUseCase

    @Before
    fun setup() {
        saveUserUseCase = SaveUserUseCase(repository)
    }

    @Test
    fun `invoke should call repository saveUser`() = runTest {
        val user = UserEntity(name = "Sara", age = 25, jobTitle = "Designer", gender = "Female")

        coEvery { repository.saveUser(user) } returns Unit

        saveUserUseCase(user)

        coVerify(exactly = 1) { repository.saveUser(user) }
    }
}