package com.dca.takanimali.model

data class RedeemHistoryModel(
    val success: Boolean,
    val data: List<RedeemHistoryItem>,
)