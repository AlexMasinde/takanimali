package com.dca.takanimali.data

import com.dca.takanimali.model.UserDetails


sealed interface AuthResource {
    data class Success(val user: UserDetails?) : AuthResource
    object Loading : AuthResource
    object NoUser: AuthResource
}


