package com.example.takanimali.ui.login

data class LoginUiState(
    val emailError: String? = null,
    val passwordError: String? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
)
