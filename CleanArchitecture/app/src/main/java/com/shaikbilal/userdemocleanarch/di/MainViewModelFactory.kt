package com.shaikbilal.userdemocleanarch.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shaikbilal.userdemocleanarch.domain.usecase.GetUsersUseCase
import com.shaikbilal.userdemocleanarch.presentation.viewmodel.MainViewModel

class MainViewModelFactory(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(getUsersUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}