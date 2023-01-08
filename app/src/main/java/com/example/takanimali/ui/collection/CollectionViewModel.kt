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
import com.example.takanimali.model.CollectionItem
import com.example.takanimali.model.CollectionResponse
import com.example.takanimali.model.UserCollectionItemResponse
import com.example.takanimali.ui.utils.wasteTypeList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class CollectionViewModel @Inject constructor(
    private val collectionHistoryRepository: CollectionHistoryRepository,
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


    fun retry() {
        collectionListState = CollectionHistoryResource.Loading
        fetchCollection()
    }

    private fun fetchCollection() {
        val accessTokenResponse = state.get<String>("accessToken")
        val userIdResponse = state.get<Int>("user_id")
        if (accessTokenResponse != null && userIdResponse != null) {
            Log.d("collection fetch", accessTokenResponse)
            val accessToken = "Bearer $accessTokenResponse"
            viewModelScope.launch {
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
                    }
                    collectionListState = CollectionHistoryResource.Success(list)
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
        }
    }


    init {
        Log.d("Test", "View model launched")
        fetchCollection()
    }


}




