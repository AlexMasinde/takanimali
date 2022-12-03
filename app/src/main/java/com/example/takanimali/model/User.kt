package com.example.takanimali.model

data class User(
    val block: Block,
    val block_id: Int,
    val created_at: String,
    val email: String,
    val email_verified_at: Any,
    val id: Int,
    val is_verified: Int,
    val location: Location,
    val location_id: Int,
    val name: String,
    val phone_number: String,
    val role_id: Int,
    val unique_id: String,
    val updated_at: String,
    val verification_code: String,
    val verified_at: String,
    val zone: Zone,
    val zone_id: Int
)