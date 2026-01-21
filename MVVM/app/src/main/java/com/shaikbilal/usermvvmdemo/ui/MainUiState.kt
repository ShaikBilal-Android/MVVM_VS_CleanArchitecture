package com.shaikbilal.usermvvmdemo.ui

import com.shaikbilal.usermvvmdemo.data.datamodels.User

sealed class MainUiState {
    object Loading : MainUiState()
    data class Success(val user : List<User>) : MainUiState()
    data class Error(val msg : String) : MainUiState()
}