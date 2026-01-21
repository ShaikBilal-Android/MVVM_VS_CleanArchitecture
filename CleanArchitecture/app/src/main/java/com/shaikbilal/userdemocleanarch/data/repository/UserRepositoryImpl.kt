package com.shaikbilal.userdemocleanarch.data.repository

import com.shaikbilal.userdemocleanarch.R
import com.shaikbilal.userdemocleanarch.domain.model.User
import com.shaikbilal.userdemocleanarch.domain.repository.UserRepository
import kotlinx.coroutines.delay

class UserRepositoryImpl : UserRepository {

    override suspend fun getUsers(): List<User> {
        delay(1500)
        return listOf(
            User(1, "Shaik Bilal", 3, R.drawable.ic_launcher_foreground),
            User(2,"Android Developer",4,R.drawable.ic_launcher_foreground),
            User(3,"Deep Dive",2,R.drawable.ic_launcher_foreground),
            User(4,"MVVM vs Clean Architecture",3,R.drawable.ic_launcher_foreground),
        )
    }
}