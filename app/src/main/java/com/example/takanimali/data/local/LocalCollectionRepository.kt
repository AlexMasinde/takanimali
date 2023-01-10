package com.example.takanimali.data.local

import com.example.takanimali.model.CollectionHistoryLocalModel
import com.example.takanimali.model.CollectionItem

interface LocalCollectionRepository {
    suspend fun setCollection(collectionHistory: CollectionHistoryLocalModel)
    suspend fun getCollection(): CollectionHistoryLocalModel
    suspend fun deleteCollection()
}