package com.example.takanimali.model

data class CollectRequestModel(
    val block_id: Int,
    val location_id: Int,
    val quantity: Float,
    val team_leader_id: Int,
    val user_id: Int,
    val waste_id: Int,
    val waste_type: Int,
    val zone_id: Int
)