package com.dca.takanimali.data

import com.dca.takanimali.model.CollectionResponse

interface CollectionHistoryRepository {
    suspend fun getCollection(accessToken: String, userId: Int): CollectionResponse
}






