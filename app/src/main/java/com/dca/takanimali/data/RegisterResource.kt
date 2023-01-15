package com.dca.takanimali.data

import com.dca.takanimali.model.UserBody


sealed interface RegisterResource {
    data class Registered(val user: UserBody?) : RegisterResource
    object Loading : RegisterResource
    object NotRegistered: RegisterResource
}