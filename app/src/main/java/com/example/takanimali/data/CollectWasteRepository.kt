package com.example.takanimali.data

import com.example.takanimali.model.CollectResponseModel
import com.example.takanimali.model.ReportWasteResponseModel

interface CollectWasteRepository {
    suspend fun reportWaste(
        block_id: Int,
        waste_id: Int,
        waste_type_id: Int,
        zone_id: Int,
        token: String
    ): ReportWasteResponseModel

    suspend fun collectWaste(
        block_id: Int,
        location_id: Int,
        quantity: Int,
        team_leader_id: Int,
        user_id: Int,
        waste_id: Int,
        waste_type: Int,
        zone_id: Int,
        token: String
    ): CollectResponseModel
}