package com.example.takanimali.data

data class UserCollectionItemResponse(
    val block: Block,
    val block_id: Int,
    val created_at: String,
    val date: String,
    val id: Int,
    val location: Location,
    val location_id: Int,
    val quantity: Int,
    val status: String,
    val team_leader_id: Int,
    val updated_at: String,
    val user_id: Int,
    val waste: Waste,
    val waste_id: Int,
    val waste_type: String,
    val zone: Zone,
    val zone_id: Int
)