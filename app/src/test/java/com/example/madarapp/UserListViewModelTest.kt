package com.example.madarapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.entity.UserEntity
import com.example.domain.usecase.GetUsersUseCase
import com.example.madarapp.ui.list_screen.UserListViewModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UserListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val getUsersUseCase: GetUsersUseCase = mockk()
    private lateinit var viewModel: UserListViewModel
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = UserListViewModel(getUsersUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadUsers should update users LiveData with UI models`() = runTest {
        // Given
        val domainUsers = listOf(UserEntity(1, "Sara", 25, "Designer", "Female"))
        coEvery { getUsersUseCase() } returns domainUsers

        // When
        viewModel.loadUsers()

        // Then
        val result = viewModel.users.value
        assertEquals(1, result?.size)
        assertEquals("Sara", result?.get(0)?.name)
        assertEquals("25", result?.get(0)?.age) // Verifies Int to String conversion
    }
}