package com.example.takanimali.data.local

import com.example.takanimali.model.CollectionHistoryLocalModel

interface LocalCollectionRepository {
    suspend fun setCollection(collectionHistory: CollectionHistoryLocalModel)
    suspend fun getCollection(): List<CollectionHistoryLocalModel>
    suspend fun deleteCollection()
}