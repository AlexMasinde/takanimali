package com.example.takanimali.data

import com.example.takanimali.model.PointsTotalResponse
import com.example.takanimali.model.RedeemHistoryModel
import com.example.takanimali.model.RedeemPointsResponse
import com.example.takanimali.network.ApiService
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