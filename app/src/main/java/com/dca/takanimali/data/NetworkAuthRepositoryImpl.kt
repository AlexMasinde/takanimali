package com.dca.takanimali.data

import com.dca.takanimali.model.AuthModel
import com.dca.takanimali.model.AuthRequestModel
import com.dca.takanimali.network.ApiService
import javax.inject.Inject


class NetworkAuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : NetworkAuthRepository {
    override suspend fun login(email: String, password: String): AuthModel =
        apiService.login(AuthRequestModel(email, password))
}