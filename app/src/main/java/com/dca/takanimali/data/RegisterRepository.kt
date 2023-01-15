package com.dca.takanimali.data

import com.dca.takanimali.model.ResendCodeResponse
import com.dca.takanimali.model.UserBody
import com.dca.takanimali.model.VerifyResponseBody

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
    suspend fun resendCode(email: String): ResendCodeResponse
}