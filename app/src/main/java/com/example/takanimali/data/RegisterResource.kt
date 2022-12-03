package com.example.takanimali.data

import com.example.takanimali.model.UserBody


sealed interface RegisterResource {
    data class Registered(val user: UserBody?) : RegisterResource
    object Loading : RegisterResource
    object NotRegistered: RegisterResource
}