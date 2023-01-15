package com.dca.takanimali.data.local

import com.dca.takanimali.model.CollectionHistoryLocalModel

interface LocalCollectionRepository {
    suspend fun setCollection(collectionHistory: CollectionHistoryLocalModel)
    suspend fun getCollection(): List<CollectionHistoryLocalModel>
    suspend fun deleteCollection()
}