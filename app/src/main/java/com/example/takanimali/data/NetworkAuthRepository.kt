package com.example.takanimali.data

import com.example.takanimali.model.AuthModel


interface NetworkAuthRepository {
    suspend fun login(email: String, password: String): AuthModel
}