package com.example.takanimali.model

data class PointsTotalResponse(
    val success: Boolean,
    val data: PointsTotalResponseDetails
)

data class PointsTotalResponseDetails(
    val total_pending_waste: String,
    val total_lifetime_waste: String,
    val total_unredeemed_points: Float
)


