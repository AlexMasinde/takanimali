package com.example.takanimali.ui.auth

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.takanimali.data.AuthResource
import com.example.takanimali.data.NetworkAuthRepository
import com.example.takanimali.data.local.LocalAuthRepository
import com.example.takanimali.model.AuthenticatedUser
import com.example.takanimali.model.CurrentUser
import com.example.takanimali.model.UserDetails
import com.google.gson.Gson
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val networkAuthRepository: NetworkAuthRepository,
    private val localAuthRepository: LocalAuthRepository,
    private val state: SavedStateHandle
) : ViewModel() {
    var authUserResponseData: AuthenticatedUser by mutableStateOf(AuthenticatedUser())
        private set

    private val _authenticatedUser = MutableStateFlow(CurrentUser())
    val authenticatedUser: StateFlow<CurrentUser> = _authenticatedUser.asStateFlow()

    var userState: AuthResource by mutableStateOf(AuthResource.Loading)
        private set

    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState: StateFlow<LoginUiState> = _loginUiState.asStateFlow()

    var loginFormState = mutableStateOf(LoginFormState())
        private set

    val NO_USER = AuthResource.NoUser


    //Commence login
    fun login() {
        _loginUiState.update { currentState ->
            currentState.copy( loginNetworkError = false)
        }
        val email = loginFormState.value.email
        val password = loginFormState.value.password

        emailValidation(email)
        passwordValidation(password)

        if (_loginUiState.value.loginUiError) return

        viewModelScope.launch {
            userState = AuthResource.Loading
            try {
                val user = networkAuthRepository.login(email, password)
                authUserResponseData.details = user
                val details = authUserResponseData.details!!.user
                val token = authUserResponseData.details!!.access_token
                val userToSave = UserDetails(
                    id = details.id,
                    access_token = token,
                    email = details.email,
                    name = details.name,
                    role_id = details.role_id,
                    phone_number = details.phone_number,
                    location = details.location.name,
                    zone = details.zone.name,
                    block = details.block.name,
                    unique_id = details.unique_id,
                )
                state["user_id"] = userToSave.id
                _authenticatedUser.update { currentState ->
                    currentState.copy(
                        details = userToSave
                    )
                }

                localAuthRepository.setUser(userToSave)
                Log.d("User auth details", "Net ${user.user.email}")
                userState = AuthResource.Success(userToSave)
                updateInterface()
            } catch (e: IOException) {
                Log.d("User auth err1", "Network failure ${e.message}")
                _loginUiState.update { currentState ->
                    currentState.copy(
                        IOAuthError = "Check your internet connection and try again",
                        loginNetworkError = true
                    )
                }
                userState = NO_USER
            } catch (e: HttpException) {
                Log.d("User auth err4", "${e.code()}")
                val errorMessage = checkHttpResponseErrorCode(e.code())
                _loginUiState.update { currentState ->
                    currentState.copy(HTTPAuthError = errorMessage, loginNetworkError = true)
                }
                userState = NO_USER
            } finally {

            }
        }

    }

    //Logout user
    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            val user = authenticatedUser.value.details

            if (user != null) {
                localAuthRepository.deleteUser(user)
            }
        }
        userState = NO_USER
    }

    //Update UI
    private fun updateInterface() {
        _loginUiState.update { currentState ->
            currentState.copy(
                IOAuthError = null,
                HTTPAuthError = null,
            )
        }
        loginFormState.value = loginFormState.value.copy(email = "", password = "")

    }

    //Get user input
    //Email
    fun onEmailChange(newEmail: String) {
        if (_loginUiState.value.passwordError != null)
            _loginUiState.value = _loginUiState.value.copy(emailError = null)
        else
            clearErrors("email")
        loginFormState.value = loginFormState.value.copy(email = newEmail)
    }


    //Password
    fun onPasswordChange(newPassword: String) {
        if (_loginUiState.value.emailError != null)
            _loginUiState.value = _loginUiState.value.copy(passwordError = null)
        else
            clearErrors("password")
        loginFormState.value = loginFormState.value.copy(password = newPassword)
    }

    //update login errors
    private fun updateLoginErrors(emailError: String? = null, passwordError: String? = null) {
        emailError?.let {
            _loginUiState.update { currentState ->
                currentState.copy(emailError = emailError, loginUiError = true)
            }
        }
        passwordError?.let {
            _loginUiState.update { currentState ->
                currentState.copy(passwordError = passwordError, loginUiError = true)
            }
        }
    }

    //username validation check
    private fun emailValidation(email: String) {
        if (email.isBlank())
            updateLoginErrors(emailError = "Please enter your email address to continue")
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            updateLoginErrors(emailError = "Invalid email")
    }

    // password validation check
    private fun passwordValidation(password: String) {
        if (password.isBlank())
            updateLoginErrors(passwordError = "Please enter your password to continue")
        else if (password.length < 5)
            updateLoginErrors(passwordError = "Password too short")
    }

    //Check response error codes
    private fun checkHttpResponseErrorCode(code: Int): String {
        return if (code == 401)
            "Wrong email or password"
        else
            "Could not login! Please try again later"
    }

    //Clear errors
    private fun clearErrors(field: String) {
        if (field == "email")
            _loginUiState.update { currentState ->
                currentState.copy(
                    emailError = null,
                    loginUiError = false,
                    HTTPAuthError = null,
                    IOAuthError = null
                )
            }
        else
            _loginUiState.update { currentState ->
                currentState.copy(
                    passwordError = null,
                    loginUiError = false,
                    HTTPAuthError = null,
                    IOAuthError = null
                )
            }
    }

    fun getUserFromDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            userState = try {
                val savedUser = localAuthRepository.getUser()
                Log.d("saved user object", Gson().toJson(savedUser))
                _authenticatedUser.update { currentState ->
                    currentState.copy(
                        details = savedUser

                    )
                }
                state["user_id"] = savedUser.id
                state["accessToken"] = savedUser.access_token
                AuthResource.Success(savedUser)
            } catch (error: IndexOutOfBoundsException) {
                Log.d("saved user object", "No user found in database")
                AuthResource.NoUser
            }

        }
    }
}