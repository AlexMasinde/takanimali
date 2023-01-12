package com.example.takanimali.ui.collection

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.takanimali.data.CollectionHistoryRepository
import com.example.takanimali.data.CollectionHistoryResource
import com.example.takanimali.data.local.LocalCollectionRepository
import com.example.takanimali.model.*
import com.example.takanimali.ui.utils.wasteTypeList
import com.google.gson.Gson
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
class CollectionViewModel @Inject constructor(
    private val collectionHistoryRepository: CollectionHistoryRepository,
    private val localCollectionRepository: LocalCollectionRepository,
    private val state: SavedStateHandle
) : ViewModel() {
    var userCollectionResponse: CollectionResponse by mutableStateOf(CollectionResponse())
        private set

    var collectionListState: CollectionHistoryResource by mutableStateOf(CollectionHistoryResource.Loading)

    private val _collectionList = MutableStateFlow(listOf<CollectionItem>())
    val collectionList: StateFlow<List<CollectionItem>> = _collectionList.asStateFlow()

    private fun getCollectionItem(userCollectionItemResponse: UserCollectionItemResponse): CollectionItem {
        val wasteType =
            wasteTypeList.filter { it.id == userCollectionItemResponse.waste_type.toInt() }
        val wasteTypeName = wasteType[0].name
        return CollectionItem(
            id = userCollectionItemResponse.id,
            date = userCollectionItemResponse.date,
            location = userCollectionItemResponse.location.name,
            waste = userCollectionItemResponse.waste.name,
            waste_type = wasteTypeName,
            quantity = userCollectionItemResponse.quantity
        )
    }

    fun clearCollectionHistoryData() {
        val emptyCollectionList: List<CollectionItem> = listOf<CollectionItem>()
        _collectionList.update {
            emptyCollectionList
        }
    }

    fun retry() {
        Log.d("Refresh function", "Function Launched")
        collectionListState = CollectionHistoryResource.Loading
        fetchCollection()
    }

    fun deleteCollectionHistory() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("Clearing data", "Clear collection executed")
            localCollectionRepository.deleteCollection()
        }
    }

    private fun fetchCollection() {
        Log.d("Refresh function", "Fetching collection history")
        val accessTokenResponse = state.get<String>("accessToken")
        val userIdResponse = state.get<Int>("user_id")
        Log.d("Refresh function", "Fetching collection stage 2")
        if (accessTokenResponse != null && userIdResponse != null) {
            Log.d("Refresh token", "$accessTokenResponse $userIdResponse")
            val accessToken = "Bearer $accessTokenResponse"
            viewModelScope.launch(Dispatchers.IO) {
                accessCollection(accessToken, userIdResponse)
            }
        }
    }

    suspend fun accessCollection(accessToken: String, userIdResponse: Int) {
        try {
            val responseData =
                collectionHistoryRepository.getCollection(accessToken, userIdResponse)
            val list = responseData.data?.map {
                getCollectionItem(it)
            }
            if (list != null) {
                _collectionList.update {
                    list
                }
                val listToStore = CollectionListLocal(
                    collectionListLocal = list
                )
                val objectToStore = CollectionHistoryLocalModel(
                    id = 1,
                    collection = listToStore
                )
                localCollectionRepository.setCollection(objectToStore)
            }
            collectionListState = CollectionHistoryResource.Success
        } catch (e: IOException) {
            Log.d("collection fetch error", "Network failure ${e.message}")
            collectionListState =
                CollectionHistoryResource.Error(error = "Could not fetch collection history! Check your connection and try again")
        } catch (e: HttpException) {
            Log.d("collection fetch error", "Http Error ${e.message}")
            collectionListState =
                CollectionHistoryResource.Error(error = "Access to collection history denied! Sign in and try again")
        }
    }

    init {
        Log.d("Collection test", "View model launched")
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("Collection test", "successfully launched scope")
            val rawCollection = localCollectionRepository.getCollection()
            Log.d("Collection Data", Gson().toJson(rawCollection))
            if (rawCollection.isNotEmpty()) {
                val rawCollectionValue = rawCollection[0]
                val list = rawCollectionValue.collection.collectionListLocal
                _collectionList.update {
                    list
                }
                collectionListState = CollectionHistoryResource.Success
            } else {
                Log.d("Collection data", "fetching from database")
                val accessTokenResponse = state.get<String>("accessToken")
                val userIdResponse = state.get<Int>("user_id")
                Log.d("Refresh function", "Fetching collection stage 2")
                if (accessTokenResponse == null) {
                    Log.d("Collection data", "No token found")
                }
                if (accessTokenResponse != null && userIdResponse != null) {
                    Log.d("Refresh token", "$accessTokenResponse $userIdResponse")
                    val accessToken = "Bearer $accessTokenResponse"
                    accessCollection(accessToken, userIdResponse)
                }
            }
        }
    }
}




