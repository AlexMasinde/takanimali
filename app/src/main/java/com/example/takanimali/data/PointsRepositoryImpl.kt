package com.example.takanimali.data

import com.example.takanimali.model.RedeemHistoryModel
import com.example.takanimali.network.ApiService
import javax.inject.Inject

class PointsRepositoryImpl @Inject constructor(private val apiService: ApiService) : PointsRepository {
    override suspend fun userRedeemHistory(token: String, userId: Int): RedeemHistoryModel =
        apiService.userRedeemHistory(token, userId)
}