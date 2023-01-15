package com.dca.takanimali.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dca.takanimali.model.Points

@Dao
interface PointsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPoints(points: Points)

    @Query("DELETE FROM points WHERE id = 1")
    suspend fun deletePoints()

    @Query("SELECT * from points")
    suspend fun getPoints(): List<Points>
}