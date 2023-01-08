package com.example.takanimali.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.takanimali.model.UserDetails

@Database(entities = [UserDetails::class], version = 1, exportSchema = false)
abstract class TakaNiMaliDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao

}