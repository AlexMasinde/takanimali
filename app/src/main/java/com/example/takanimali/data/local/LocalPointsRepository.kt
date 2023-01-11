package com.example.takanimali.data.local

import com.example.takanimali.model.Points

interface LocalPointsRepository {
    suspend fun getPoints(): List<Points>
    suspend fun setPoints(points: Points)
    suspend fun deletePoints()

}