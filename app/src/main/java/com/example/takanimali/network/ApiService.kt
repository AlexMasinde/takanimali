package com.example.takanimali.network

import com.example.takanimali.model.*
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("auth/login")
    suspend fun login(@Body authRequestModel: AuthRequestModel): AuthModel

    @Headers("Content-Type: application/json")
    @POST("auth/register")
    suspend fun register(@Body registerRequestModel: RegisterRequestModel): UserBody

    @Headers("Content-Type: application/json")
    @POST("auth/verify")
    suspend fun verify(@Body verificationRequestModel: VerificationRequestModel): VerifyResponseBody

    @Headers("Content-Type: application/json")
    @POST("waste/sighting")
    suspend fun report(@Header("Authorization") token: String, @Body collectWasteRequestModel: ReportWasteRequestModel): ReportWasteResponseModel

    @Headers("Content-Type: application/json")
    @POST("waste/collect")
    suspend fun collect(@Header("Authorization") token: String, @Body collectRequestModel: CollectRequestModel): CollectResponseModel

}