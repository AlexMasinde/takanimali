package com.dca.takanimali.model

sealed class WasteTypeListItem (val id: Int, val name: String) {
    object Plastic: WasteTypeListItem(1, "Plastic")
    object Clothes: WasteTypeListItem(2, "Clothes")
    object Organic: WasteTypeListItem(3, "Organic")
    object Others: WasteTypeListItem(4, "Others")

}

