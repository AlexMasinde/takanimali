package com.example.takanimali.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.takanimali.model.UserDetails

@Database(entities = [UserDetails::class], version = 1, exportSchema = false)
abstract class TakaNiMaliDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: TakaNiMaliDatabase? = null
        fun getDatabase(context: Context): TakaNiMaliDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TakaNiMaliDatabase::class.java,
                    "item_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}