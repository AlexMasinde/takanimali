package com.dca.takanimali.data.local

import com.dca.takanimali.model.Points

interface LocalPointsRepository {
    suspend fun getPoints(): List<Points>
    suspend fun setPoints(points: Points)
    suspend fun deletePoints()

}