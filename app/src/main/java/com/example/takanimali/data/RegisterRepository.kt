package com.example.takanimali.data

import com.example.takanimali.model.AuthModel
import com.example.takanimali.model.UserBody
import com.example.takanimali.model.VerifyResponseBody

interface RegisterRepository {
    suspend fun register(
        block_id: Int,
        email: String,
        location_id: Int,
        name: String,
        password: String,
        phone_number: String,
        zone_id: Int
    ): UserBody
    suspend fun verify(verification_code: String): VerifyResponseBody
}