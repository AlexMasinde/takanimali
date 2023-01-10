package com.example.takanimali.ui.collect

data class CollectUiState(
    val idError: String = "",
    val quantityError: String = "",
    val IOAuthError: String = "",
    val HTTPAuthError: String = "",
    val collectUiError: Boolean = false,
    val collectNetworkError: Boolean = false
)
