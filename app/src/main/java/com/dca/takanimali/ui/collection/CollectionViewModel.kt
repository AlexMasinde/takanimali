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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
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


    private val _collectionListState =
        MutableStateFlow<CollectionHistoryResource>(CollectionHistoryResource.Loading)
    val collectionListState = _collectionListState.asStateFlow()

    private val _collectionList = MutableStateFlow(listOf<CollectionItem>())
    val collectionList: StateFlow<List<CollectionItem>> = _collectionList.asStateFlow()


    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.IO + viewModelJob)

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
        Log.d("Refresh function", "Function Launched")
        _collectionListState.update {
            CollectionHistoryResource.Loading
        }
        fetchCollection()
    }

    fun deleteCollectionHistory() {
        uiScope.launch {
            localCollectionRepository.deleteCollection()
        }
        val emptyCollectionList: List<CollectionItem> = listOf()
        _collectionList.update {
            emptyCollectionList
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
            _collectionListState.update {
                CollectionHistoryResource.Success
            }
        } catch (e: IOException) {
            if (collectionList.value.isEmpty()) {
                _collectionListState.update {
                    CollectionHistoryResource.Error(error = "Could not fetch collection history! Check your connection and try again")
                }
            }
        } catch (e: HttpException) {
            _collectionListState.update {
                CollectionHistoryResource.Error(error = "Access to collection history denied! Sign in and try again")
            }
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
                _collectionListState.update {
                    CollectionHistoryResource.Success
                }
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




