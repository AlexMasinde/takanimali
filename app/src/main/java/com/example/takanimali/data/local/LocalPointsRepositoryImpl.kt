package com.example.takanimali.data.local

import com.example.takanimali.model.Points
import javax.inject.Inject

class LocalPointsRepositoryImpl @Inject constructor(private val pointsDao: PointsDao): LocalPointsRepository {
    override suspend fun getPoints(): Points {
        val points = pointsDao.getPoints()
        return points[0]
    }

    override suspend fun setPoints(points: Points) {
        pointsDao.insertPoints(points)
    }

    override suspend fun deletePoints() {
        pointsDao.deletePoints()
    }
}