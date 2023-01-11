package com.example.takanimali.data.local

import com.example.takanimali.model.Points
import javax.inject.Inject

class LocalPointsRepositoryImpl @Inject constructor(private val pointsDao: PointsDao): LocalPointsRepository {
    override suspend fun getPoints(): List<Points> {
        return pointsDao.getPoints()
    }

    override suspend fun setPoints(points: Points) {
        pointsDao.insertPoints(points)
    }

    override suspend fun deletePoints() {
        pointsDao.deletePoints()
    }
}