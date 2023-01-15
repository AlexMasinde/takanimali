package com.dca.takanimali.model

data class RedeemPointsResponseData(
    val created_at: String,
    val id: Int,
    val status: String,
    val total_amount: Float,
    val total_points: Float,
    val total_waste_collected: String,
    val updated_at: String,
    val user_id: String
)