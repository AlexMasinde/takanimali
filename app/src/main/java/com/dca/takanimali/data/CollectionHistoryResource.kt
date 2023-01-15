package com.dca.takanimali.data

sealed interface CollectionHistoryResource {
    object Success: CollectionHistoryResource
    object Loading : CollectionHistoryResource
    data class Error(val error: String?): CollectionHistoryResource
}