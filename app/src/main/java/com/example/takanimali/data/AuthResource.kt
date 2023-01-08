package com.example.takanimali.data

import com.example.takanimali.model.UserDetails


sealed interface AuthResource {
    data class Success(val user: UserDetails?) : AuthResource
    object Loading : AuthResource
    object NoUser: AuthResource
}


