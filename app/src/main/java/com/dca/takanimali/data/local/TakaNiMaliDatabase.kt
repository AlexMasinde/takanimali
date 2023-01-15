package com.dca.takanimali.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dca.takanimali.model.*

@Database(
    entities = [UserDetails::class, CollectionHistoryLocalModel::class, Points::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(CollectionHistoryConverter::class, PointsConverter::class)
abstract class TakaNiMaliDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun historyDao(): HistoryDao
    abstract fun pointsDao(): PointsDao
}