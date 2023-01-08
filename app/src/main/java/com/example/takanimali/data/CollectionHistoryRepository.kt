package com.example.takanimali.data

import com.example.takanimali.model.CollectionResponse

interface CollectionHistoryRepository {
    suspend fun getCollection(accessToken: String, userId: Int): CollectionResponse
}






