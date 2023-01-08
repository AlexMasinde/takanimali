package com.example.takanimali.ui.register

data class RegisterUiState(
    val emailError: String? = null,
    val passwordError: String? = null,
    val phoneNumberError: String? = null,
    val nameError: String? = null,
    val IOAuthError: String? = null,
    val HTTPAuthError: String? = null,
    val registerUiError: Boolean = false,
    val registerNetworkError: Boolean = false
)
