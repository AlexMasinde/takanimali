package com.dca.takanimali.data

import com.dca.takanimali.model.CollectionResponse
import com.dca.takanimali.network.ApiService
import javax.inject.Inject

class CollectionHistoryRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    CollectionHistoryRepository {
    override suspend fun getCollection(accessToken: String, userId: Int): CollectionResponse =
        apiService.collectionHistory(accessToken, userId)
}

