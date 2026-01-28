package com.example.data.repository

import com.example.data.datasource.UserOfflineDataSource
import com.example.data.model.UserEntityRoom
import com.example.domain.entity.UserEntity
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class UserRepositoryImplTest {

    private val dataSource: UserOfflineDataSource = mockk()
    private lateinit var repository: UserRepositoryImpl

    @Before
    fun setup() {
        repository = UserRepositoryImpl(dataSource)
    }

    @Test
    fun `getUsers should fetch from datasource and map to domain list`() = runTest {
        val roomUsers = listOf(
            UserEntityRoom(1, "Ahmad", 28, "Dev", "Male")
        )
        coEvery { dataSource.getUsers() } returns roomUsers

        val result = repository.getUsers()

        assertEquals(1, result.size)
        assertEquals("Ahmad", result[0].name)
        coVerify(exactly = 1) { dataSource.getUsers() }
    }

    @Test
    fun `saveUser should map domain entity to room and call datasource`() = runTest {
        // Given
        val domainUser = UserEntity(name = "Sara", age = 24, jobTitle = "Designer", gender = "Female")
        coEvery { dataSource.saveUser(any()) } just Runs

        repository.saveUser(domainUser)

        coVerify {
            dataSource.saveUser(match {
                it.name == "Sara" && it.jobTitle == "Designer"
            })
        }
    }
}