package com.example.takanimali.ui.login

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {
    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState: StateFlow<LoginUiState> = _loginUiState.asStateFlow()

    var loginFormState = mutableStateOf(LoginFormState())
        private set


    //Commence login
    fun login() {
        val email = loginFormState.value.email
        val password = loginFormState.value.password
        //Validate email and password and return if there is an error
        emailValidation(email)
        passwordValidation(password)

        if (_loginUiState.value.inputError) return


        Log.d("Auth detail errors", "No errors detected in email and password")

    }


    fun onEmailChange(newEmail: String) {
        if (_loginUiState.value.passwordError != null)
            _loginUiState.value = _loginUiState.value.copy(emailError = null)
        else
            _loginUiState.update { currentState ->
                currentState.copy(emailError = null, inputError = false)
            }
        loginFormState.value = loginFormState.value.copy(email = newEmail)
    }

    fun onPasswordChange(newPassword: String) {
        if (_loginUiState.value.emailError != null)
            _loginUiState.value = _loginUiState.value.copy(passwordError = null)
        else
            _loginUiState.update { currentState ->
                currentState.copy(passwordError = null, inputError = false)
            }
        loginFormState.value = loginFormState.value.copy(password = newPassword)
    }

    //update login errors
    private fun updateLoginErrors(emailError: String? = null, passwordError: String? = null) {
        emailError?.let {
            _loginUiState.update { currentState ->
                currentState.copy(emailError = emailError, inputError = true)
            }
        }
        passwordError?.let {
            _loginUiState.update { currentState ->
                currentState.copy(passwordError = passwordError, inputError = true)
            }
        }
    }

    //username validation check
    private fun emailValidation(email: String) {
        if (email.isBlank())
            updateLoginErrors(emailError = "Email cannot be blank")
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            updateLoginErrors(emailError = "Invalid email")
    }

    // password validation check
    private fun passwordValidation(password: String) {
        if (password.isBlank())
            updateLoginErrors(passwordError = "Password cannot be blank")
        else if (password.length < 5)
            updateLoginErrors(passwordError = "Password too short")
    }
}