package com.example.takanimali.data

import com.example.takanimali.model.AuthModel
import com.example.takanimali.model.AuthRequestModel
import com.example.takanimali.network.ApiService
import javax.inject.Inject


class NetworkAuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : NetworkAuthRepository {
    override suspend fun login(email: String, password: String): AuthModel =
        apiService.login(AuthRequestModel(email, password))
}