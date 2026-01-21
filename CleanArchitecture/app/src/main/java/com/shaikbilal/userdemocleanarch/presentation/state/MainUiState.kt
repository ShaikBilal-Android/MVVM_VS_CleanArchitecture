package com.shaikbilal.userdemocleanarch.presentation.state

import com.shaikbilal.userdemocleanarch.domain.model.User

sealed class MainUiState {
    object Loading : MainUiState()
    data class Success(val user : List<User>) : MainUiState()
    data class Error(val msg : String) : MainUiState()
}