package com.example.takanimali.data

import com.example.takanimali.model.RedeemHistoryModel

interface PointsRepository {
    suspend fun userRedeemHistory(token: String, userId: Int): RedeemHistoryModel
}