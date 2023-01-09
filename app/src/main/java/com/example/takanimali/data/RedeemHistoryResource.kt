package com.example.takanimali.data

import com.example.takanimali.model.RedeemHistoryItem

sealed interface RedeemHistoryResource {
    object Success : RedeemHistoryResource
    object Loading : RedeemHistoryResource
    object Error : RedeemHistoryResource
}