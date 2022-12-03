package com.example.takanimali.data

import com.example.takanimali.model.RegisterRequestModel
import com.example.takanimali.model.UserBody
import com.example.takanimali.model.VerificationRequestModel
import com.example.takanimali.model.VerifyResponseBody
import com.example.takanimali.network.ApiService

class RegisterRepositoryImpl(private val apiService: ApiService) : RegisterRepository {
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
        )
    )

    override suspend fun verify(verification_code: String): VerifyResponseBody = apiService.verify(
        VerificationRequestModel(verification_code)
    )

}