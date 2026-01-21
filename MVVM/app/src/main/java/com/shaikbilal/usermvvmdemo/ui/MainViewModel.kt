package com.shaikbilal.usermvvmdemo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shaikbilal.usermvvmdemo.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    val userRepository: UserRepository = UserRepository()
): ViewModel() {
    private var _uiState = MutableStateFlow<MainUiState>(MainUiState.Loading)
    val uiState : StateFlow<MainUiState> = _uiState

    fun loadUsers(){
        viewModelScope.launch {
            _uiState.value = MainUiState.Loading
            try {
                val user = userRepository.getUsers()
                _uiState.value = MainUiState.Success(user)
            }catch (e: Exception){
                _uiState.value = MainUiState.Error("Failed to load...")
            }
        }
    }
}