package com.dca.takanimali.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson

@Entity(tableName = "collection")
data class CollectionHistoryLocalModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val collection: CollectionListLocal
)

data class CollectionListLocal(
    val collectionListLocal: List<CollectionItem>
)


class CollectionHistoryConverter {
    @TypeConverter
    fun fromCollectionString(value: String?): CollectionListLocal {
        return Gson().fromJson(value, CollectionListLocal::class.java)
    }

    @TypeConverter
    fun toCollectionString(list: CollectionListLocal): String {
        return Gson().toJson(list)
    }
}