package com.shaikbilal.userdemocleanarch.domain.usecase

import com.shaikbilal.userdemocleanarch.domain.model.User
import com.shaikbilal.userdemocleanarch.domain.repository.UserRepository

class GetUsersUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(): List<User> {
        return repository.getUsers()
    }
}