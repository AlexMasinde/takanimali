package com.dca.takanimali.di

import com.dca.takanimali.data.CollectWasteRepository
import com.dca.takanimali.data.NetworkAuthRepository
import com.dca.takanimali.data.RegisterRepository
import com.dca.takanimali.ui.auth.AuthViewModel

interface AppContainer {
    val authRepository: NetworkAuthRepository
    val authViewModel: AuthViewModel
    val registerRepository: RegisterRepository
    val collectWasteRepository: CollectWasteRepository
}