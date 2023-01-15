package com.dca.takanimali.data.local

import com.dca.takanimali.model.UserDetails
import javax.inject.Inject

class LocalAuthRepositoryImpl @Inject constructor(private val userDao: UserDao) :
    LocalAuthRepository {
    override suspend fun setUser(userDetails: UserDetails) {
        userDao.insertUser(userDetails)
    }

    override suspend fun getUser(): UserDetails {
        val userArray = userDao.getUser()
        return userArray[0]
    }

    override suspend fun deleteUser(userDetails: UserDetails) {
       userDao.deleteUser(userDetails)
    }
}

