package com.example.takanimali.data

import com.example.takanimali.model.PointsTotalResponse
import com.example.takanimali.model.RedeemHistoryModel
import com.example.takanimali.model.RedeemPointsResponse

interface PointsRepository {
    suspend fun userRedeemHistory(token: String): RedeemHistoryModel
    suspend fun userTotalPoints(token: String, id: Int): PointsTotalResponse
    suspend fun redeemPoints(token: String, id: Int): RedeemPointsResponse
}