package com.example.takanimali.ui.points

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.takanimali.data.PointsRepository
import com.example.takanimali.data.RedeemHistoryResource
import com.example.takanimali.model.PointsTotalResponse
import com.example.takanimali.model.PointsTotalResponseDetails
import com.example.takanimali.model.RedeemHistoryItem
import com.example.takanimali.model.TotalPointsDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.time.LocalDateTime
import javax.inject.Inject


@HiltViewModel
class PointsViewModel @Inject constructor(
    private val pointsRepository: PointsRepository,
    private val state: SavedStateHandle
) : ViewModel() {

    var redeemHistoryState: RedeemHistoryResource by mutableStateOf(RedeemHistoryResource.Loading)
    private val _redeemHistory = MutableStateFlow(listOf<RedeemHistoryItem>())
    val redeemHistory: StateFlow<List<RedeemHistoryItem>> = _redeemHistory.asStateFlow()

    private val _pointsTotal = MutableStateFlow(TotalPointsDetails())
    val pointsTotal: StateFlow<TotalPointsDetails> = _pointsTotal.asStateFlow()

    var redeemError = mutableStateOf("")
        private set


    fun retry() {
        redeemHistoryState = RedeemHistoryResource.Loading
        getRedeemHistory()
    }

    fun redeemPoints() {
        Log.d("Running redeem points", "function invoked")
        val availablePoints = pointsTotal.value.details?.total_unredeemed_points
        if (availablePoints?.toInt() == 0 || availablePoints == null) {
            redeemError.value = "You do not have points to redeem"
            return
        }
        val accessTokenResponse = state.get<String>("accessToken")
        val userIdResponse = state.get<Int>("user_id")
        if (accessTokenResponse != null && userIdResponse != null) {
            val accessToken = "Bearer $accessTokenResponse"
            viewModelScope.launch {
                try {
                    redeemHistoryState = RedeemHistoryResource.Loading
                    val response = pointsRepository.redeemPoints(accessToken, userIdResponse)
                    _pointsTotal.update { currentState ->
                        currentState.copy(
                            details = pointsTotal.value.details?.let {
                                PointsTotalResponseDetails(
                                    total_lifetime_waste = it.total_lifetime_waste,
                                    total_pending_waste = "0",
                                    total_unredeemed_points = 0F
                                )
                            }
                        )
                    }
                    val responseData = response.data
                    val newRedeemHistoryListItem = RedeemHistoryItem(
                        created_at = responseData.created_at,
                        updated_at = responseData.updated_at,
                        id = responseData.id,
                        total_amount = responseData.total_amount.toString(),
                        total_points = responseData.total_points.toString(),
                        user_id = responseData.user_id.toInt(),
                        total_waste_collected = responseData.total_waste_collected,
                        status = responseData.status
                    )
                    val currentRedeemHistory = _redeemHistory.value
                    val newRedeemHistory: List<RedeemHistoryItem> =
                        currentRedeemHistory.plus(newRedeemHistoryListItem)
                    _redeemHistory.update {
                        newRedeemHistory
                    }
                    redeemHistoryState = RedeemHistoryResource.Success
                } catch (e: IOException) {
                    redeemError.value = "Could not redeem! Please check your connection"
                    redeemHistoryState = RedeemHistoryResource.Success
                } catch (e: HttpException) {
                    redeemError.value = "Server Error"
                    redeemHistoryState = RedeemHistoryResource.Success
                }
            }
        }
    }


    private fun getRedeemHistory() {
        val accessTokenResponse = state.get<String>("accessToken")
        val userIdResponse = state.get<Int>("user_id")
        if (accessTokenResponse != null && userIdResponse != null) {
            val accessToken = "Bearer $accessTokenResponse"
            viewModelScope.launch {
                redeemHistoryState = try {
                    val historyResponse = pointsRepository.userRedeemHistory(accessToken)
                    val totalPointsResponse =
                        pointsRepository.userTotalPoints(accessToken, userIdResponse)
                    val historyData = historyResponse.data
                    val totalPointsData = totalPointsResponse.data
                    _redeemHistory.update {
                        historyData
                    }
                    _pointsTotal.update { currentState ->
                        currentState.copy(
                            details = totalPointsData
                        )
                    }
                    RedeemHistoryResource.Success
                } catch (e: IOException) {
                    Log.d("Redeem Error", "${e.message}")
                    RedeemHistoryResource.Error
                } catch (e: HttpException) {
                    Log.d("Redeem Error", "${e.message}")
                    RedeemHistoryResource.Error
                }

            }
        }

    }

    init {
        getRedeemHistory()
    }
}