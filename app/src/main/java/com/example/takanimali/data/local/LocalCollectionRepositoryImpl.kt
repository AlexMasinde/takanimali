package com.example.takanimali.data.local

import android.util.Log
import com.example.takanimali.model.CollectionHistoryLocalModel
import javax.inject.Inject

class LocalCollectionRepositoryImpl @Inject constructor(private val historyDao: HistoryDao) :
    LocalCollectionRepository {
    override suspend fun setCollection(collectionHistory: CollectionHistoryLocalModel) {
        historyDao.insertCollectionItem(collectionHistory)
    }

    override suspend fun getCollection(): List<CollectionHistoryLocalModel> {
        return historyDao.getCollectionHistory()
    }

    override suspend fun deleteCollection() {
        historyDao.deleteCollection()
    }
}