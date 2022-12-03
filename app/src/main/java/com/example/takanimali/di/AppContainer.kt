package com.example.takanimali.di

import com.example.takanimali.data.CollectWasteRepository
import com.example.takanimali.data.NetworkAuthRepository
import com.example.takanimali.data.RegisterRepository
import com.example.takanimali.ui.auth.AuthViewModel

interface AppContainer {
    val authRepository: NetworkAuthRepository
    val authViewModel: AuthViewModel
    val registerRepository: RegisterRepository
    val collectWasteRepository: CollectWasteRepository
}