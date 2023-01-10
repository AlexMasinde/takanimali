package com.example.takanimali.data.local

import androidx.room.*
import com.example.takanimali.model.CollectionHistoryLocalModel


@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCollectionItem(collectionHistoryLocalModel: CollectionHistoryLocalModel)

    @Query("DELETE from collection")
    suspend fun deleteCollection()

    @Query("SELECT * from collection")
    fun getCollectionHistory(): List<CollectionHistoryLocalModel>

}


