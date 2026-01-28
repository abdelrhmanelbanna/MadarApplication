package com.example.domain.usecase

import com.example.domain.entity.UserEntity
import com.example.domain.repository.UserRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetUsersUseCaseTest {

    private val repository: UserRepository = mockk()
    private lateinit var getUsersUseCase: GetUsersUseCase

    @Before
    fun setup() {
        getUsersUseCase = GetUsersUseCase(repository)
    }

    @Test
    fun `invoke should return user list from repository`() = runTest {

        val mockUsers = listOf(
            UserEntity(
                name = "Ahmed",
                age = 28,
                jobTitle = "Dev",
                gender = "Male"
            )
        )
        coEvery { repository.getUsers() } returns mockUsers

        val result = getUsersUseCase()

        assertEquals(mockUsers, result)
    }
}