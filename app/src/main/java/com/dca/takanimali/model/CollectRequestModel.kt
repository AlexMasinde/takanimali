package com.dca.takanimali.model

data class CollectRequestModel(
    val block_id: Int,
    val location_id: Int,
    val quantity: Int,
    val team_leader_id: Int,
    val unique_id: String,
    val waste_id: Int,
    val waste_type: Int,
    val zone_id: Int
)