package com.example.takanimali.data.local

import android.util.Log
import com.example.takanimali.model.CollectionHistoryLocalModel
import com.google.gson.Gson
import javax.inject.Inject

class LocalCollectionRepositoryImpl @Inject constructor(private val historyDao: HistoryDao) :
    LocalCollectionRepository {
    override suspend fun setCollection(collectionHistory: CollectionHistoryLocalModel) {
        historyDao.insertCollectionItem(collectionHistory)
    }

    override suspend fun getCollection(): CollectionHistoryLocalModel {
        val response =  historyDao.getCollectionHistory()
        Log.d("Collection Data", Gson().toJson(response))
        return response[0]
    }

    override suspend fun deleteCollection() {
        historyDao.deleteCollection()
    }
}