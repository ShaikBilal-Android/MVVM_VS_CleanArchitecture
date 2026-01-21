package com.shaikbilal.usermvvmdemo.data.repository

import com.shaikbilal.usermvvmdemo.R
import com.shaikbilal.usermvvmdemo.data.datamodels.User
import kotlinx.coroutines.delay

class UserRepository {

    suspend fun getUsers() : List<User>{
        delay(1500)
        return listOf(
            User(1,"Shaik Bilal",29,R.drawable.ic_launcher_foreground),
            User(2,"Android Developer",5,R.drawable.ic_launcher_foreground),
            User(3,"Deep Dive",29,R.drawable.ic_launcher_foreground),
            User(4,"MVVM vs Clean Architecture",29,R.drawable.ic_launcher_foreground),
        )
    }
}