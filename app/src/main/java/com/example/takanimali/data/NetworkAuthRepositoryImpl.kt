package com.example.takanimali.data

import com.example.takanimali.model.AuthModel
import com.example.takanimali.network.ApiService

class AuthRepositoryImpl (private val apiService: ApiService): AuthRepository {
    override suspend fun login(): AuthModel = apiService.login()
}