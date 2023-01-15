package com.dca.takanimali.model

sealed class BlockListItem (val id: Int, val name: String) {
    object BlockOne : BlockListItem(1, "Block-1")
    object BlockTwo : BlockListItem(2, "Block-2")
    object BlockThree : BlockListItem(3, "Block-3")
    object BlockFour : BlockListItem(4, "Block-4")
    object BlockFive : BlockListItem(5, "Block-5")
}