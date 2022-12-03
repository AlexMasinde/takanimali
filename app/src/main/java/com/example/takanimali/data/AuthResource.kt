package com.example.takanimali.data

import com.example.takanimali.model.AuthModel
import kotlinx.coroutines.flow.StateFlow


sealed interface AuthResource {
    data class Success(val user: AuthModel?) : AuthResource
    object Loading : AuthResource
    object NoUser: AuthResource
}


