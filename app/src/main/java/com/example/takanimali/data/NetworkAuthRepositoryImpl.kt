package com.example.takanimali.data

import com.example.takanimali.model.AuthModel
import com.example.takanimali.model.AuthRequestModel
import com.example.takanimali.network.ApiService


class NetworkAuthRepositoryImpl(
    private val apiService: ApiService,
) : NetworkAuthRepository {
    override suspend fun login(email: String, password: String): AuthModel =
        apiService.login(AuthRequestModel(email, password))
}