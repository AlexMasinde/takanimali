package com.example.takanimali.data

import android.util.Log
import com.example.takanimali.model.CollectionResponse
import com.example.takanimali.network.ApiService
import javax.inject.Inject

class CollectionHistoryRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    CollectionHistoryRepository {
    override suspend fun getCollection(accessToken: String, userId: Int): CollectionResponse =
        apiService.collectionHistory(accessToken, userId)
}

