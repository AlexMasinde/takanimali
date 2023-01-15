package com.dca.takanimali.data

import com.dca.takanimali.model.PointsTotalResponse
import com.dca.takanimali.model.RedeemHistoryModel
import com.dca.takanimali.model.RedeemPointsResponse

interface PointsRepository {
    suspend fun userRedeemHistory(token: String): RedeemHistoryModel
    suspend fun userTotalPoints(token: String, id: Int): PointsTotalResponse
    suspend fun redeemPoints(token: String, id: Int): RedeemPointsResponse
}