package com.example.takanimali.ui.points

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.takanimali.data.PointsRepository
import com.example.takanimali.data.RedeemHistoryResource
import com.example.takanimali.model.RedeemHistoryItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class PointsViewModel @Inject constructor(
    private val pointsRepository: PointsRepository,
    private val state: SavedStateHandle
) : ViewModel() {

    var redeemHistoryState: RedeemHistoryResource by mutableStateOf(RedeemHistoryResource.Loading)
    private val _redeemHistory = MutableStateFlow(listOf<RedeemHistoryItem>())
    val redeemHistory: StateFlow<List<RedeemHistoryItem>> = _redeemHistory.asStateFlow()

    fun retry() {
        redeemHistoryState = RedeemHistoryResource.Loading
        getRedeemHistory()
    }


    private fun getRedeemHistory() {
        val accessTokenResponse = state.get<String>("accessToken")
        val userIdResponse = state.get<Int>("user_id")
        if (accessTokenResponse != null && userIdResponse != null) {
            val accessToken = "Bearer $accessTokenResponse"
            viewModelScope.launch {
                redeemHistoryState = try {
                    val historyResponse = pointsRepository.userRedeemHistory(accessToken, userIdResponse)
                    val historyData = historyResponse.data
                    _redeemHistory.update {
                        historyData
                    }
                    RedeemHistoryResource.Success(historyData)
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