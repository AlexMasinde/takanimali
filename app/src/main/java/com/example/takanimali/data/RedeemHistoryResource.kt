package com.example.takanimali.data

import com.example.takanimali.model.RedeemHistoryItem

sealed interface RedeemHistoryResource {
    data class Success(val redeemHistory: List<RedeemHistoryItem>?) : RedeemHistoryResource
    object Loading : RedeemHistoryResource
    object Error: RedeemHistoryResource
}