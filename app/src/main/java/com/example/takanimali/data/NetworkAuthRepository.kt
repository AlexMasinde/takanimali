package com.example.takanimali.data

import com.example.takanimali.model.AuthModel

interface AuthRepository {
    suspend fun login():AuthModel
}