package com.dca.takanimali.ui.points

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dca.takanimali.data.PointsRepository
import com.dca.takanimali.data.RedeemHistoryResource
import com.dca.takanimali.data.local.LocalPointsRepository
import com.dca.takanimali.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class PointsViewModel @Inject constructor(
    private val pointsRepository: PointsRepository,
    private val localPointsRepository: LocalPointsRepository,
    private val state: SavedStateHandle
) : ViewModel() {

    var redeemHistoryState: RedeemHistoryResource by mutableStateOf(RedeemHistoryResource.Loading)
    private val _redeemHistory = MutableStateFlow(listOf<RedeemHistoryItem>())
    val redeemHistory: StateFlow<List<RedeemHistoryItem>> = _redeemHistory.asStateFlow()

    private val _pointsTotal = MutableStateFlow(TotalPointsDetails())
    val pointsTotal: StateFlow<TotalPointsDetails> = _pointsTotal.asStateFlow()

    var redeemError = mutableStateOf("")
        private set

    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    fun clearPointsHistory() {
        val emptyPointsHistory = listOf<RedeemHistoryItem>()
        _redeemHistory.update {
            emptyPointsHistory
        }
        _pointsTotal.update {
            TotalPointsDetails()
        }
    }

    fun retry() {
        getRedeemHistory()
    }

     fun deletePointsCollection() {
        viewModelScope.launch (Dispatchers.IO) {
            Log.d("Clearing", "Clear points executed")
            localPointsRepository.deletePoints()
        }
    }

    fun redeemPoints() {
        val availablePoints = pointsTotal.value.details?.total_unredeemed_points
        if (availablePoints?.toInt() == 0 || availablePoints == null) {
            redeemError.value = "You do not have points to redeem"
            return
        }
        val accessTokenResponse = state.get<String>("accessToken")
        val userIdResponse = state.get<Int>("user_id")
        if (accessTokenResponse != null && userIdResponse != null) {
            val accessToken = "Bearer $accessTokenResponse"
            uiScope.launch {
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

                    val redeemHistory = RedeemHistory(
                        redeemHistoryItemList = newRedeemHistory
                    )
                    val pointsToSave = Points(
                        id = 1,
                        total_lifetime_waste = "0",
                        total_pending_waste = "0",
                        total_unredeemed_points = 0F,
                        history = redeemHistory
                    )
                    localPointsRepository.setPoints(pointsToSave)

                    redeemHistoryState = RedeemHistoryResource.Success
                } catch (e: IOException) {
                    redeemError.value = "Could not redeem! Please check your connection"
                    redeemHistoryState = RedeemHistoryResource.Success
                    delay(5000)
                    redeemError.value = ""
                } catch (e: HttpException) {
                    redeemError.value = when (e.code()) {
                        401 -> "Redeem request not authorized"
                        403 -> "Redeem request not authorized"
                        400 -> "You already have a pending redeem request"
                        else -> "Could not redeem! Please contact admin"
                    }
                    redeemHistoryState = RedeemHistoryResource.Success
                    delay(5000)
                    redeemError.value = ""
                }
            }
        }
    }

    suspend fun accessPoints(accessToken: String, userIdResponse: Int) {
       try {
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
            val redeemHistory = RedeemHistory(
                redeemHistoryItemList = historyData
            )
            val pointsToSave = Points(
                id = 1,
                total_lifetime_waste = totalPointsData.total_lifetime_waste,
                total_pending_waste = totalPointsData.total_pending_waste,
                total_unredeemed_points = totalPointsData.total_unredeemed_points,
                history = redeemHistory
            )
            localPointsRepository.setPoints(pointsToSave)
            redeemHistoryState = RedeemHistoryResource.Success
        } catch (e: IOException) {
            Log.d("Redeem Error", "${e.message}")
            redeemHistoryState = RedeemHistoryResource.Error
        } catch (e: HttpException) {
            Log.d("Redeem Error", "${e.message}")
            redeemHistoryState = RedeemHistoryResource.Error
        }
    }

    private fun getRedeemHistory() {
        val accessTokenResponse = state.get<String>("accessToken")
        val userIdResponse = state.get<Int>("user_id")
        if (accessTokenResponse != null && userIdResponse != null) {
            val accessToken = "Bearer $accessTokenResponse"
            uiScope.launch {
                redeemHistoryState = RedeemHistoryResource.Loading
                accessPoints(accessToken, userIdResponse)
            }
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val rawPointsResponse = localPointsRepository.getPoints()
            if (rawPointsResponse.isNotEmpty()) {
                val pointsResponseValue = rawPointsResponse[0]
                val pointsList = pointsResponseValue.history.redeemHistoryItemList
                val pointsTotal = PointsTotalResponseDetails(
                    total_unredeemed_points = pointsResponseValue.total_unredeemed_points,
                    total_pending_waste = pointsResponseValue.total_pending_waste,
                    total_lifetime_waste = pointsResponseValue.total_lifetime_waste
                )
                _redeemHistory.update {
                    pointsList
                }
                _pointsTotal.update { currentState ->
                    currentState.copy(
                        details = pointsTotal
                    )
                }
                redeemHistoryState = RedeemHistoryResource.Success
            } else {
                val accessTokenResponse = state.get<String>("accessToken")
                val userIdResponse = state.get<Int>("user_id")
                if (accessTokenResponse != null && userIdResponse != null) {
                    val accessToken = "Bearer $accessTokenResponse"
                    accessPoints(accessToken, userIdResponse)
                }
            }
        }
    }
}