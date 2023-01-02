package com.example.takanimali.model

data class RedeemHistoryItem(
    val created_at: String,
    val id: Int,
    val status: String,
    val total_amount: String,
    val total_points: String,
    val total_waste_collected: String,
    val updated_at: String,
    val user_id: Int
)