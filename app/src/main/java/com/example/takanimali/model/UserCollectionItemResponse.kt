package com.example.takanimali.model

import com.example.takanimali.model.Location
import com.example.takanimali.model.Waste

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


data class Location(
    val created_at: Any,
    val deleted_at: Any,
    val id: Int,
    val name: String,
    val updated_at: Any
)

data class Waste(
    val created_at: Any,
    val deleted_at: Any,
    val id: Int,
    val name: String,
    val points_per_kg: Int,
    val price_per_kg: Int,
    val updated_at: Any,
    val waste_type_id: Int
)