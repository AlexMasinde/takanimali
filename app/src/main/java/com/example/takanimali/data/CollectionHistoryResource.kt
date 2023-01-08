package com.example.takanimali.data

import com.example.takanimali.model.CollectionItem

sealed interface CollectionHistoryResource {
    data class Success(val collectionList: List<CollectionItem>?) : CollectionHistoryResource
    object Loading : CollectionHistoryResource
    data class Error(val error: String?): CollectionHistoryResource
}