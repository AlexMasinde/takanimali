package com.example.takanimali.model

sealed class LocationListItem (val id: Int, val name: String){
    object Kakuma: LocationListItem(1, "Kakuma")
    object Kalobeyei: LocationListItem(2, "Kalobeyei")
}
