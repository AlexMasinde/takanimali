package com.example.takanimali.model

sealed class WasteListItem (val id: Int, val name: String, val waste_type_id: Int) {
    object PET: WasteListItem(1, "PET", 1)
    object Yellow: WasteListItem(2, "Yellow", 1)
    object White: WasteListItem(3, "White", 1)
    object Pvc: WasteListItem(4, "Pvc", 1)
    object LDPE: WasteListItem(5, "LDPE", 1)
    object PP: WasteListItem(6, "PP", 1)
    object PS: WasteListItem(7, "PS", 1)
    object Others: WasteListItem(8, "Others", 1)
    object Clothes: WasteListItem(9, "Clothes", 2)
    object Sbag: WasteListItem(10, "S-bag", 2)
    object Sacks: WasteListItem(11, "Sacks", 2)
    object Food: WasteListItem(12, "Food", 3)
    object Leaves: WasteListItem(13, "Leaves", 3)
    object Glass: WasteListItem(14, "Glass", 4)
    object Metal: WasteListItem(15, "Metal", 4)
    object Shoes: WasteListItem(16, "Shoes", 4)
    object Paper: WasteListItem(17, "Paper", 4)
    object Bones: WasteListItem(18, "Bones", 4)

}

