package com.example.takanimali.data.local

import com.example.takanimali.model.UserDetails

interface LocalAuthRepository {
    suspend fun setUser(userDetails: UserDetails)
    suspend fun getUser(): UserDetails
    suspend fun deleteUser(userDetails: UserDetails)
}