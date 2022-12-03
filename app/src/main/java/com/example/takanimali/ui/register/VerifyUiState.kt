package com.example.takanimali.ui.register

data class VerifyUiState(
    val codeError: String? = null,
    val IOAuthError: String? = null,
    val HTTPAuthError: String? = null,
    val verifyUiError: Boolean  = false
)
