package com.example.takanimali.model

import com.example.takanimali.model.User

data class AuthModel(
    val access_token: String,
    val msg: String,
    val status: String,
    val user: User
)