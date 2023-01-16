package com.dca.takanimali.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user")
data class UserDetails(
    @PrimaryKey()
    @SerializedName("id")
    val databaseId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("access_token")
    val access_token: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("role_id")
    val role_id: Int,
    @SerializedName("unique_id")
    val unique_id: String,
    @SerializedName("phone_number")
    val phone_number: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("zone")
    val zone: String,
    @SerializedName("block")
    val block: String,
)
