package com.dca.takanimali.di

import com.dca.takanimali.data.*
import com.dca.takanimali.network.ApiService
import com.dca.takanimali.ui.auth.AuthViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DefaultAppContainer : AppContainer {

    override lateinit var authViewModel: AuthViewModel

    private val BASE_URL = "https://takanimali.amband.co.ke/api/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL).build()

    private val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }


    override val authRepository: NetworkAuthRepository by lazy {
        NetworkAuthRepositoryImpl(apiService)
    }

    override val registerRepository: RegisterRepository by lazy {
        RegisterRepositoryImpl(apiService)
    }

    override val collectWasteRepository: CollectWasteRepository by lazy {
        CollectWasteRepositoryImpl(apiService)
    }

}