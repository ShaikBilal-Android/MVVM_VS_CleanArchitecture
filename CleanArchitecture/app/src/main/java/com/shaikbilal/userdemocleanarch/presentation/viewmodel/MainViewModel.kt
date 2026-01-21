package com.shaikbilal.userdemocleanarch.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shaikbilal.userdemocleanarch.domain.usecase.GetUsersUseCase
import com.shaikbilal.userdemocleanarch.presentation.state.MainUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<MainUiState>(MainUiState.Loading)
    val uiState: StateFlow<MainUiState> = _uiState

    fun loadUsers() {
        viewModelScope.launch {
            _uiState.value = MainUiState.Loading
            try {
                val users = getUsersUseCase()
                _uiState.value = MainUiState.Success(users)
            } catch (e: Exception) {
                _uiState.value = MainUiState.Error("Failed to load...")
            }
        }
    }
}
