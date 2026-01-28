package com.example.madarapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.usecase.SaveUserUseCase
import com.example.madarapp.ui.input_screen.InputViewModel
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
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
class InputViewModelTest {

    // 1. Executes each task synchronously for LiveData
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val saveUserUseCase: SaveUserUseCase = mockk()
    private lateinit var viewModel: InputViewModel
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = InputViewModel(saveUserUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `saveUser with valid data calls use case and sets success`() = runTest {
        // Given
        coEvery { saveUserUseCase(any()) } just Runs

        // When
        viewModel.saveUser("Ahmad", "28", "Dev", "Male")

        // Then
        assertEquals(true, viewModel.saveSuccess.value) // Verifies LiveData state
        coVerify { saveUserUseCase(any()) } // Verifies UseCase was called
    }

    @Test
    fun `saveUser with empty data sets success to false and does not call use case`() = runTest {
        // When
        viewModel.saveUser("", "", "", "")

        // Then
        assertEquals(false, viewModel.saveSuccess.value)
        coVerify(exactly = 0) { saveUserUseCase(any()) }
    }
}