package com.dca.takanimali.data

import com.dca.takanimali.model.*
import com.dca.takanimali.network.ApiService
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    RegisterRepository {
    override suspend fun register(
        block_id: Int,
        email: String,
        location_id: Int,
        name: String,
        password: String,
        phone_number: String,
        zone_id: Int
    ): UserBody = apiService.register(
        RegisterRequestModel(
            block_id,
            email,
            location_id,
            name,
            password,
            phone_number,
            zone_id
        ),
        "application/json"
    )

    override suspend fun verify(verification_code: String): VerifyResponseBody = apiService.verify(
        VerificationRequestModel(verification_code)
    )

    override suspend fun resendCode(email: String): ResendCodeResponse =
        apiService.resendVerificationCode(
            ResendCodeModel(email)
        )
}