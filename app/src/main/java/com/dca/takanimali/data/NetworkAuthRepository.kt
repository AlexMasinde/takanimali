package com.dca.takanimali.data

import com.dca.takanimali.model.AuthModel


interface NetworkAuthRepository {
    suspend fun login(email: String, password: String): AuthModel
}