package com.dca.takanimali.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dca.takanimali.model.UserDetails

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: UserDetails)

    @Delete
    suspend fun deleteUser(user: UserDetails)

    @Query("SELECT * from user")
    fun getUser(): List<UserDetails>
}



