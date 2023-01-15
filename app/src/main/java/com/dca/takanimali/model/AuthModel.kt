package com.dca.takanimali.model

data class AuthModel(
    val access_token: String,
    val msg: String,
    val status: String,
    val user: User
)