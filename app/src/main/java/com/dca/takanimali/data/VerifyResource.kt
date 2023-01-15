package com.dca.takanimali.data

sealed interface VerifyResource {
    object Verified : VerifyResource
    object Loading : VerifyResource
    object NotVerified : VerifyResource
}