package com.example.takanimali.ui.login

data class LoginUiState(
    val emailError: String? = null,
    val passwordError: String? = null,
    val inputError: Boolean  = false
)
