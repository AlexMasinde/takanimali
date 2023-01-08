package com.example.takanimali.network

import com.example.takanimali.model.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

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
    @POST("auth/resend/code")
    suspend fun resendVerificationCode(@Body resendCodeModel: ResendCodeModel): ResendCodeResponse

    @Headers("Content-Type: application/json")
    @POST("auth/forgot_password")
    suspend fun forgotPassword(@Body resendCodeModel: ResendCodeModel): ResendCodeResponse

    @Headers("Content-Type: application/json")
    @POST("waste/sighting")
    suspend fun report(
        @Header("Authorization") token: String,
        @Body collectWasteRequestModel: ReportWasteRequestModel
    ): ReportWasteResponseModel

    @Headers("Content-Type: application/json")
    @POST("waste/collect")
    suspend fun collect(
        @Header("Authorization") token: String,
        @Body collectRequestModel: CollectRequestModel
    ): CollectResponseModel

    @Headers("Content-Type: application/json")
    @GET("waste/collected/{id}")
    suspend fun collectionHistory(
        @Header("Authorization") token: String,
        @Path("id") int: Int
    ): CollectionResponse

    @Headers("Content-Type: application/json")
    @GET("https://takanimali.amband.co.ke/api/waste/user/redemption/{id}")
    suspend fun userRedeemHistory(
        @Header("Authorization") token: String,
        @Path("id") int: Int
    ): RedeemHistoryModel

}