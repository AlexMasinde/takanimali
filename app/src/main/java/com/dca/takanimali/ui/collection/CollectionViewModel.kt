package com.dca.takanimali.ui.collection

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dca.takanimali.data.CollectionHistoryRepository
import com.dca.takanimali.data.CollectionHistoryResource
import com.dca.takanimali.data.local.LocalCollectionRepository
import com.dca.takanimali.model.*
import com.dca.takanimali.ui.utils.wasteTypeList
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
            localCollectionRepository.deleteCollection()
        }
    }

    private fun fetchCollection() {
        val accessTokenResponse = state.get<String>("accessToken")
        val userIdResponse = state.get<Int>("user_id")
        if (accessTokenResponse != null && userIdResponse != null) {
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
            collectionListState =
                CollectionHistoryResource.Error(error = "Could not fetch collection history! Check your connection and try again")
        } catch (e: HttpException) {
            collectionListState =
                CollectionHistoryResource.Error(error = "Access to collection history denied! Sign in and try again")
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val rawCollection = localCollectionRepository.getCollection()
            if (rawCollection.isNotEmpty()) {
                val rawCollectionValue = rawCollection[0]
                val list = rawCollectionValue.collection.collectionListLocal
                _collectionList.update {
                    list
                }
                collectionListState = CollectionHistoryResource.Success
            } else {
                val accessTokenResponse = state.get<String>("accessToken")
                val userIdResponse = state.get<Int>("user_id")
                if (accessTokenResponse != null && userIdResponse != null) {
                    val accessToken = "Bearer $accessTokenResponse"
                    accessCollection(accessToken, userIdResponse)
                }
            }
        }
    }
}




