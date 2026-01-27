package com.example.madarapp.ui.input_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.UserEntity
import com.example.domain.usecase.SaveUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class InputViewModel @Inject constructor(
    private val saveUserUseCase: SaveUserUseCase
) : ViewModel() {

    private val _saveSuccess = MutableLiveData<Boolean>()
    val saveSuccess: LiveData<Boolean> = _saveSuccess

    fun saveUser(
        name: String,
        age: String,
        jobTitle: String,
        gender: String
    ) {
        if (name.isBlank() || age.isBlank() || jobTitle.isBlank() || gender.isBlank()) {
            _saveSuccess.value = false
            return
        }

        val user = UserEntity(
            name = name,
            age = age.toInt(),
            jobTitle = jobTitle,
            gender = gender
        )

        viewModelScope.launch {
            saveUserUseCase(user)
            _saveSuccess.postValue(true)
        }
    }
}

