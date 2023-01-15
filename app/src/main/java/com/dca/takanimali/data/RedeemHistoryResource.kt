package com.dca.takanimali.data

sealed interface RedeemHistoryResource {
    object Success : RedeemHistoryResource
    object Loading : RedeemHistoryResource
    object Error : RedeemHistoryResource
}