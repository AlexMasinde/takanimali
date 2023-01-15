package com.dca.takanimali.network

import com.dca.takanimali.model.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

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
    @POST("waste/sighting")
    suspend fun report(
        @Header("Authorization") token: String,
        @Body collectWasteRequestModel: ReportWasteRequestModel
    ): ReportWasteResponseModel

    @Headers("Content-Type: application/json")
    @POST("waste/collect")
    suspend fun collect(
        @Header("Authorization") token: String,
        @Body collectRequestModel: CollectRequestModel,
        @Header("accept") type: String,
    ): CollectResponseModel

    @Headers("Content-Type: application/json")
    @GET("waste/collected/{id}")
    suspend fun collectionHistory(
        @Header("Authorization") token: String,
        @Path("id") int: Int
    ): CollectionResponse

    @Headers("Content-Type: application/json")
    @GET("waste/user/redemption")
    suspend fun userRedeemHistory(
        @Header("Authorization") token: String,
    ): RedeemHistoryModel

    @Headers("Content-Type: application/json")
    @GET("waste/collection/user/points/{id}")
    suspend fun userPointsTotal(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): PointsTotalResponse

    @Headers("Content-Type: application/json")
    @GET("waste/user/redeem/all/{id}")
    suspend fun redeemPoints(
        @Header("Authorization") token: String,
        @Path("id") int: Int
    ): RedeemPointsResponse
}