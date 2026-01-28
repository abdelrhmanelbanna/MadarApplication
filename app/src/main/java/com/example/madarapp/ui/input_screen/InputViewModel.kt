package com.example.madarapp.ui.input_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.UserEntity
import com.example.domain.usecase.SaveUserUseCase
import com.example.domain.validator.FieldType
import com.example.domain.validator.UserValidator
import com.example.domain.validator.ValidationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class InputViewModel @Inject constructor(
    private val saveUserUseCase: SaveUserUseCase,
    private val validator: UserValidator // Inject the validator
) : ViewModel() {

    private val _saveStatus = MutableLiveData<ValidationResult>()
    val saveStatus: LiveData<ValidationResult> = _saveStatus

    fun saveUser(name: String, age: String, jobTitle: String, gender: String) {
        val validation = validator.validate(name, age, jobTitle, gender)

        if (validation is ValidationResult.Error) {
            _saveStatus.value = validation
            return
        }

        viewModelScope.launch {
            try {
                val user = UserEntity(
                    name = name,
                    age = age.toInt(),
                    jobTitle = jobTitle,
                    gender = gender
                )
                saveUserUseCase(user)
                _saveStatus.postValue(ValidationResult.Success)
            } catch (e: Exception) {
                _saveStatus.value = ValidationResult.Error(
                    field = FieldType.GENDER,
                    message = "Database error: ${e.localizedMessage}"
                )
            }
        }
    }
}

