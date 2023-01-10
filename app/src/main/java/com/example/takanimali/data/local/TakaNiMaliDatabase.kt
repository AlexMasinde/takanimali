package com.example.takanimali.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.takanimali.model.CollectionHistoryConverter
import com.example.takanimali.model.CollectionHistoryLocalModel
import com.example.takanimali.model.UserDetails

@Database(
    entities = [UserDetails::class, CollectionHistoryLocalModel::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(CollectionHistoryConverter::class)
abstract class TakaNiMaliDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun historyDao(): HistoryDao
}