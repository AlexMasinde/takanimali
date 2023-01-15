package com.dca.takanimali.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName


data class PointsTotalResponse(
    val success: Boolean,
    val data: PointsTotalResponseDetails
)

data class PointsTotalResponseDetails(
    val total_pending_waste: String,
    val total_lifetime_waste: String,
    val total_unredeemed_points: Float
)

@Entity(tableName = "points")
data class Points(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerializedName("total_pending_waste")
    val total_pending_waste: String,
    @SerializedName("total_lifetime_waste")
    val total_lifetime_waste: String,
    @SerializedName("total_unredeemed_points")
    val total_unredeemed_points: Float,
    val history: RedeemHistory
)


data class RedeemHistory(
    val redeemHistoryItemList: List<RedeemHistoryItem>
)

class PointsConverter {

    @TypeConverter
    fun fromPointsString(value: String): RedeemHistory {
        return Gson().fromJson(value, RedeemHistory::class.java)
    }

    @TypeConverter
    fun toPointsString(list: RedeemHistory):String {
        return  Gson().toJson(list)
    }
}

