package com.example.takanimali.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.takanimali.model.Points

@Dao
interface PointsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPoints(points: Points)

    @Query("DELETE from points")
    suspend fun deletePoints()

    @Query("SELECT * from points")
    suspend fun getPoints(): List<Points>
}