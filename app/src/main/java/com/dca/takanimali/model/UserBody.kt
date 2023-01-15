package com.dca.takanimali.model

data class UserBody(
    val access_token: String,
    val `data`: Data,
    val msg: String,
    val status: String
)