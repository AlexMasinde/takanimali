package com.example.takanimali.data

sealed interface VerificationResource {
    object Verified : VerificationResource
    object Loading : VerificationResource
    object NotVerified : VerificationResource
}