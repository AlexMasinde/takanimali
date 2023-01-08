package com.example.takanimali.data

import com.example.takanimali.model.CollectRequestModel
import com.example.takanimali.model.CollectResponseModel
import com.example.takanimali.model.ReportWasteRequestModel
import com.example.takanimali.model.ReportWasteResponseModel
import com.example.takanimali.network.ApiService
import javax.inject.Inject

class CollectWasteRepositoryImpl @Inject constructor(private val apiService: ApiService) : CollectWasteRepository {
    override suspend fun reportWaste(
        block_id: Int,
        waste_id: Int,
        waste_type_id: Int,
        zone_id: Int,
        token: String
    ): ReportWasteResponseModel = apiService.report(
        token,
        ReportWasteRequestModel(block_id, waste_id, waste_type_id, zone_id)
    )

    override suspend fun collectWaste(
        block_id: Int,
        location_id: Int,
        quantity: Int,
        team_leader_id: Int,
        user_id: Int,
        waste_id: Int,
        waste_type: Int,
        zone_id: Int,
        token: String
    ): CollectResponseModel = apiService.collect(
        token,
        CollectRequestModel(
            block_id,
            location_id,
            quantity,
            team_leader_id,
            user_id,
            waste_id,
            waste_type,
            zone_id
        )
    )
}