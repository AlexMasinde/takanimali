package com.dca.takanimali.data.local

import androidx.room.*
import com.dca.takanimali.model.CollectionHistoryLocalModel


@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCollectionItem(collectionHistoryLocalModel: CollectionHistoryLocalModel)

    @Query("DELETE FROM collection WHERE id = 1")
    suspend fun deleteCollection()

    @Query("SELECT * from collection")
    fun getCollectionHistory(): List<CollectionHistoryLocalModel>

}


