package com.example.takanimali.model

data class CollectionResponse (
    val message: String? = null,
    val data: List<UserCollectionItemResponse>? = null,
    val success: Boolean = false
)