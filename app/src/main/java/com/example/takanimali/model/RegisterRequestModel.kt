package com.example.takanimali.model

data class RegisterRequestModel(
    val block_id: Int,
    val email: String,
    val location_id: Int,
    val name: String,
    val password: String,
    val phone_number: String,
    val zone_id: Int
)