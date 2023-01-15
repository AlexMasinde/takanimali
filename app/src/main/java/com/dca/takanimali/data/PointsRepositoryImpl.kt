package com.dca.takanimali.data

import com.dca.takanimali.model.PointsTotalResponse
import com.dca.takanimali.model.RedeemHistoryModel
import com.dca.takanimali.model.RedeemPointsResponse
import com.dca.takanimali.network.ApiService
import javax.inject.Inject

class PointsRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    PointsRepository {
    override suspend fun userRedeemHistory(token: String): RedeemHistoryModel =
        apiService.userRedeemHistory(token)

    override suspend fun userTotalPoints(token: String, id: Int): PointsTotalResponse =
        apiService.userPointsTotal(token, id)

    override suspend fun redeemPoints(token: String, id: Int): RedeemPointsResponse =
        apiService.redeemPoints(token, id)
}