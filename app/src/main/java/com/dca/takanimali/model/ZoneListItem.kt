package com.dca.takanimali.model

sealed class ZoneListItem(val id: Int, val name:String, val location_id: Int) {
    object KakumaOne: ZoneListItem(1, "Kakuma-1", 1)
    object KakumaTwo: ZoneListItem(2, "Kakuma-2", 1)
    object KakumaThree: ZoneListItem(3, "Kakuma-3", 1)
    object KakumaFour: ZoneListItem(4, "Kakuma-4", 1)
    object VillageOne: ZoneListItem(5, "Village-1", 2)
    object VillageTwo: ZoneListItem(6, "Village-2", 2)
    object VillageThree: ZoneListItem(7, "Village-3", 2)
}