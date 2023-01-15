package com.dca.takanimali.model

data class ReportWasteRequestModel(
    val block_id: Int,
    val waste_id: Int,
    val waste_type_id: Int,
    val zone_id: Int
)