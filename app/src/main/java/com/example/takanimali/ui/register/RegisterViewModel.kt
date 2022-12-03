package com.example.takanimali.ui.register

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.takanimali.TakaNiMaliApplication
import com.example.takanimali.data.AuthResource
import com.example.takanimali.data.RegisterRepository
import com.example.takanimali.data.RegisterResource
import com.example.takanimali.data.VerificationResource
import com.example.takanimali.model.*
import com.example.takanimali.ui.auth.AuthViewModel
import com.example.takanimali.ui.utils.initialWasteList
import com.example.takanimali.ui.utils.initialZoneList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class RegisterViewModel(private val registerRepository: RegisterRepository) : ViewModel() {
    var registerState: RegisterResource by mutableStateOf(RegisterResource.NotRegistered)
        private set

    var verifiedState: VerificationResource by mutableStateOf(VerificationResource.NotVerified)
        private set

    var verificationCode = mutableStateOf("")
        private set

    private var _verificationUiState = MutableStateFlow(VerifyUiState())
    val verifyUiState: StateFlow<VerifyUiState> = _verificationUiState.asStateFlow()


    private var _registerUiState = MutableStateFlow(RegisterUiState())
    val registerUiState: StateFlow<RegisterUiState> = _registerUiState.asStateFlow()

    var registerFormState = mutableStateOf(RegisterFormState())
        private set

    var selectedLocation: LocationListItem by mutableStateOf(LocationListItem.Kakuma)
        private set

    var selectedZone: ZoneListItem by mutableStateOf(ZoneListItem.KakumaOne)
        private set

    var selectedBlock: BlockListItem by mutableStateOf(BlockListItem.BlockOne)
        private set

    var zoneList: List<ZoneListItem> by mutableStateOf(initialZoneList.filter { it.location_id == selectedLocation.id })
        private set

    fun onVerificationCodeChange(code: String) {
        _verificationUiState.update { currentState ->
            currentState.copy(
                codeError = null,
                IOAuthError = null,
                HTTPAuthError = null,
                verifyUiError = false
            )
        }
        verificationCode.value = code
    }

    //validate verification code
    private fun validateCode(code: String) {
        if (code.isEmpty())
            _verificationUiState.update { currentState ->
                currentState.copy(
                    codeError = "Verification code cannot be empty",
                    verifyUiError = true
                )
            }
    }

    fun verifyCode() {
        val code = verificationCode.value
        validateCode(code)
        if (_verificationUiState.value.verifyUiError) return
        Log.d("no", "No error")
        viewModelScope.launch {
            verifiedState = VerificationResource.Loading
            try {
                val userBody = registerRepository.verify(code)
                Log.d("User auth Token", "${userBody.msg}")
                verifiedState = VerificationResource.Verified
            } catch (e: IOException) {
                Log.d("User auth err1", "Network failure ${e.message}")
                    _verificationUiState.update { currentState ->
                    currentState.copy(
                        IOAuthError = "Check your internet connection and try again",
                        verifyUiError = true
                    )
                }
                verifiedState = VerificationResource.NotVerified
            } catch (e: HttpException) {
                Log.d("User auth err4", "${e.code()}")
                val errorMessage = checkHttpResponseErrorCodeVerify(e.code())
                _verificationUiState.update { currentState ->
                    currentState.copy(HTTPAuthError = errorMessage, verifyUiError = true)
                }
                verifiedState = VerificationResource.NotVerified
            }
        }

    }

    private fun checkHttpResponseErrorCodeVerify(code: Int): String {
        return if (code == 404)
            "Code not found"
        else
            "Could not Verify! Please try again later"
    }

    //Commence registration
    fun register() {
        val email = registerFormState.value.email
        val password = registerFormState.value.password
        val name = registerFormState.value.name
        val phone = registerFormState.value.phone_number

        emailValidation(email)
        passwordValidation(password)
        phoneValidation(phone)
        nameValidation(name)

        if (_registerUiState.value.registerUiError) return

        viewModelScope.launch {
            registerState = RegisterResource.Loading
            try {
                val userBody = registerRepository.register(
                    selectedBlock.id,
                    email,
                    selectedLocation.id,
                    name,
                    password,
                    phone,
                    selectedZone.id
                )
                Log.d("User auth Token", "${userBody.access_token}")
                registerState = RegisterResource.Registered(userBody)
            } catch (e: IOException) {
                Log.d("User auth err1", "Network failure ${e.message}")
                _registerUiState.update { currentState ->
                    currentState.copy(
                        IOAuthError = "Check your internet connection and try again",
                        registerUiError = true
                    )
                }
                registerState = RegisterResource.NotRegistered
            } catch (e: HttpException) {
                Log.d("User auth err4", "${e.code()}")
                val errorMessage = checkHttpResponseErrorCode(e.code())
                _registerUiState.update { currentState ->
                    currentState.copy(HTTPAuthError = errorMessage, registerUiError = true)
                }
                registerState = RegisterResource.NotRegistered
            }
        }
    }


    private fun checkHttpResponseErrorCode(code: Int): String {
        return if (code == 401)
            "Check your details and try again"
        else
            "Could not login! Please try again later"
    }


    //Email user input
    fun onEmailChange(newEmail: String) {
        if (_registerUiState.value.passwordError != null || _registerUiState.value.nameError != null || _registerUiState.value.phoneNumberError != null)
            _registerUiState.value = _registerUiState.value.copy(emailError = null)
        else
            clearErrors("email")

        registerFormState.value = registerFormState.value.copy(email = newEmail)
    }


    //Password
    fun onPasswordChange(newPassword: String) {
        if (_registerUiState.value.emailError != null || _registerUiState.value.nameError != null || _registerUiState.value.phoneNumberError != null)
            _registerUiState.value = _registerUiState.value.copy(passwordError = null)
        else
            clearErrors("password")

        registerFormState.value = registerFormState.value.copy(password = newPassword)
    }

    //NameR
    fun onNameChange(newName: String) {
        if (_registerUiState.value.passwordError != null || _registerUiState.value.nameError != null || _registerUiState.value.phoneNumberError != null)
            _registerUiState.value = _registerUiState.value.copy(nameError = null)
        else
            clearErrors("email")

        registerFormState.value = registerFormState.value.copy(name = newName)
    }

    //Phone
    fun onPhoneChange(newPhone: String) {
        if (_registerUiState.value.nameError != null || _registerUiState.value.nameError != null || _registerUiState.value.phoneNumberError != null)
            _registerUiState.value = _registerUiState.value.copy(phoneNumberError = null)
        else
            clearErrors("email")

        registerFormState.value = registerFormState.value.copy(phone_number = newPhone)
    }

    fun updateLocation(locationListItem: LocationListItem) {
        selectedLocation = locationListItem
        val newZoneList = initialZoneList.filter { it.location_id == locationListItem.id }
        zoneList = newZoneList
        selectedZone = newZoneList[0]
    }

    fun updateZone(zoneListItem: ZoneListItem) {
        selectedZone = zoneListItem
    }

    fun updateBlock(blockListItem: BlockListItem) {
        selectedBlock = blockListItem
    }


    //Validation
    //username validation check
    private fun emailValidation(email: String) {
        if (email.isBlank())
            updateRegisterErrors(emailError = "Please enter your email address to continue")
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            updateRegisterErrors(emailError = "Invalid email")
    }

    // password validation check
    private fun passwordValidation(password: String) {
        if (password.isBlank())
            updateRegisterErrors(passwordError = "Please enter your password to continue")
        else if (password.length < 5)
            updateRegisterErrors(passwordError = "Password too short")
    }

    //Phone validation
    private fun phoneValidation(phone: String) {
        if (phone.isBlank())
            updateRegisterErrors(phoneNumberError = "Please enter your number to continue")
        else if (phone.length < 10)
            updateRegisterErrors(phoneNumberError = "Please enter a valid phone number")
    }

    //Name validation
    private fun nameValidation(name: String) {
        if (name.isBlank())
            updateRegisterErrors(nameError = "Please enter your name to continue")

    }


    //update login errors
    private fun updateRegisterErrors(
        emailError: String? = null,
        passwordError: String? = null,
        phoneNumberError: String? = null,
        nameError: String? = null
    ) {
        emailError?.let {
            _registerUiState.update { currentState ->
                currentState.copy(emailError = emailError, registerUiError = true)
            }
        }
        passwordError?.let {
            _registerUiState.update { currentState ->
                currentState.copy(passwordError = passwordError, registerUiError = true)
            }
        }
        nameError?.let {
            _registerUiState.update { currentState ->
                currentState.copy(nameError = nameError, registerUiError = true)
            }
        }
        phoneNumberError?.let {
            _registerUiState.update { currentState ->
                currentState.copy(phoneNumberError = phoneNumberError, registerUiError = true)
            }
        }
    }


    private fun clearErrors(field: String) {
        if (field == "email") {
            _registerUiState.update { currentState ->
                currentState.copy(
                    emailError = null,
                    registerUiError = false,
                    HTTPAuthError = null,
                    IOAuthError = null
                )
            }
        } else if (field == "password") {
            _registerUiState.update { currentState ->
                currentState.copy(
                    passwordError = null,
                    registerUiError = false,
                    HTTPAuthError = null,
                    IOAuthError = null
                )
            }
        } else if (field == "name") {
            _registerUiState.update { currentState ->
                currentState.copy(
                    nameError = null,
                    registerUiError = false,
                    HTTPAuthError = null,
                    IOAuthError = null
                )
            }
        } else {
            _registerUiState.update { currentState ->
                currentState.copy(
                    phoneNumberError = null,
                    registerUiError = false,
                    HTTPAuthError = null,
                    IOAuthError = null
                )
            }
        }

    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TakaNiMaliApplication)
                val registerRepository = application.container.registerRepository
                RegisterViewModel(registerRepository = registerRepository)
            }
        }
    }

}