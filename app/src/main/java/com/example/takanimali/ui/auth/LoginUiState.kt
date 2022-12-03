package com.example.takanimali.ui.auth

data class LoginUiState(
    val emailError: String? = null,
    val passwordError: String? = null,
    val IOAuthError: String? = null,
    val HTTPAuthError: String? = null,
    val loginUiError: Boolean  = false
)
