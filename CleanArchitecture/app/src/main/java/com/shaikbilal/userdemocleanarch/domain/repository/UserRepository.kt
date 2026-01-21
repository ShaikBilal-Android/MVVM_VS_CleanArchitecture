package com.shaikbilal.userdemocleanarch.domain.repository

import com.shaikbilal.userdemocleanarch.domain.model.User

interface UserRepository {
    suspend fun getUsers(): List<User>
}