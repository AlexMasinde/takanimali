package com.dca.takanimali.data

import com.dca.takanimali.model.CollectResponseModel
import com.dca.takanimali.model.ReportWasteResponseModel

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
        unique_id: String,
        waste_id: Int,
        waste_type: Int,
        zone_id: Int,
        token: String
    ): CollectResponseModel
}