package com.dca.takanimali.data.local

import com.dca.takanimali.model.UserDetails

interface LocalAuthRepository {
    suspend fun setUser(userDetails: UserDetails)
    suspend fun getUser(): UserDetails
    suspend fun deleteUser(userDetails: UserDetails)
}