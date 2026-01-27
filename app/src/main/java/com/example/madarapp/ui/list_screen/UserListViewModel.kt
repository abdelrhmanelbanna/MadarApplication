package com.example.madarapp.ui.list_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetUsersUseCase
import com.example.madarapp.model.UserUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _users = MutableLiveData<List<UserUiModel>>()
    val users: LiveData<List<UserUiModel>> = _users

    fun loadUsers() {
        viewModelScope.launch {
            val result = getUsersUseCase()
            _users.value = result.map {
                UserUiModel(
                    name = it.name,
                    age = it.age.toString(),
                    jobTitle = it.jobTitle,
                    gender = it.gender
                )
            }
        }
    }
}