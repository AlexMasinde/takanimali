package com.example.takanimali.ui.collect

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.takanimali.TakaNiMaliApplication
import com.example.takanimali.data.CollectResource
import com.example.takanimali.data.CollectWasteRepository
import com.example.takanimali.data.RegisterResource
import com.example.takanimali.model.*
import com.example.takanimali.ui.report.ReportViewModel
import com.example.takanimali.ui.utils.initialWasteList
import com.example.takanimali.ui.utils.initialZoneList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class CollectViewModel(private val collectWasteRepository: CollectWasteRepository) : ViewModel() {
    var collectState: CollectResource by mutableStateOf(CollectResource.NotCollected)
        private set
    private var _collectUiState = MutableStateFlow(CollectUiState())
    val collectUiState: StateFlow<CollectUiState> = _collectUiState.asStateFlow()

    var collectFormState = mutableStateOf(CollectionFormState())
        private set
    var selectedLocation: LocationListItem by mutableStateOf(LocationListItem.Kakuma)
        private set
    var selectedZone: ZoneListItem by mutableStateOf(ZoneListItem.KakumaOne)
        private set
    var selectedBlock: BlockListItem by mutableStateOf(BlockListItem.BlockOne)
        private set
    var selectedWasteType: WasteTypeListItem by mutableStateOf(WasteTypeListItem.Plastic)
        private set
    var selectedWaste: WasteListItem by mutableStateOf(WasteListItem.PET)
        private set

    var zoneList: List<ZoneListItem> by mutableStateOf(initialZoneList.filter { it.location_id == selectedLocation.id })
        private set

    var wasteList: List<WasteListItem> by mutableStateOf(initialWasteList.filter { it.waste_type_id == selectedWasteType.id })


    fun updateLocation(locationListItem: LocationListItem) {
        selectedLocation = locationListItem
        val newZoneList = initialZoneList.filter { it.location_id == locationListItem.id }
        zoneList = newZoneList
        selectedZone = newZoneList[0]
    }

    fun updateWasteType(wasteTypeListItem: WasteTypeListItem) {
        selectedWasteType = wasteTypeListItem
        val newWasteList = initialWasteList.filter { it.waste_type_id == wasteTypeListItem.id }
        wasteList = newWasteList
        selectedWaste = newWasteList[0]
    }

    fun updateWaste(wasteListItem: WasteListItem) {
        selectedWaste = wasteListItem
    }

    fun updateZone(zoneListItem: ZoneListItem) {
        selectedZone = zoneListItem
    }

    fun updateBlock(blockListItem: BlockListItem) {
        selectedBlock = blockListItem
    }

    //Commence registration
    fun collectWaste(leaderId: Int?, token: String?) {
        val userId = collectFormState.value.userId
        val quantity = collectFormState.value.quantity
        var token = "Bearer $token"

        val leaderIdToUse = leaderId ?: 1

        userIdValidation(userId)
        quantityValidation(quantity)


        if (_collectUiState.value.collectUiError) return

        viewModelScope.launch {
            collectState = CollectResource.Loading
            try {
                val userBody = collectWasteRepository.collectWaste(
                    selectedBlock.id,
                    selectedLocation.id,
                    quantity.toInt(),
                    leaderIdToUse,
                    userId.toInt(),
                    selectedWaste.id,
                    selectedWasteType.id,
                    selectedZone.id,
                    token
                )
                Log.d("User auth Token", "${userBody.success}")
                collectState = CollectResource.Collected
            } catch (e: IOException) {
                Log.d("User auth err1", "Network failure ${e.message}")
                _collectUiState.update { currentState ->
                    currentState.copy(
                        IOAuthError = "Check your internet connection and try again",
                        collectUiError = true
                    )
                }
                collectState = CollectResource.NotCollected
            } catch (e: HttpException) {
                Log.d("User auth err4", "${e.code()}")
                val errorMessage = "Operation not authorized"
                _collectUiState.update { currentState ->
                    currentState.copy(HTTPAuthError = errorMessage, collectUiError = true)
                }
                collectState = CollectResource.NotCollected
            }
        }
    }

    fun onUserIdChange(newUserId: String) {
        if (_collectUiState.value.quantityError != null)
            _collectUiState.value = _collectUiState.value.copy(idError = null)
        else
            clearErrors("id")
        collectFormState.value = collectFormState.value.copy(userId = newUserId)
    }

    fun onQuantityChange(newQuantity: String) {
        if (_collectUiState.value.idError != null)
            _collectUiState.value = _collectUiState.value.copy(quantityError = null)
        else
            clearErrors("quantity")
        collectFormState.value = collectFormState.value.copy(quantity = newQuantity)
    }

    //update collect errors
    private fun updateCollectErrors(idError: String? = null, quantityError: String? = null) {
        idError?.let {
            _collectUiState.update { currentState ->
                currentState.copy(idError = idError, collectUiError = true)
            }
        }
        quantityError?.let {
            _collectUiState.update { currentState ->
                currentState.copy(quantityError = quantityError, collectUiError = true)
            }
        }
    }

    //id validation check
    private fun userIdValidation(id: String) {
        if (id.toInt() <= 0)
            updateCollectErrors(idError = "Please enter a valid user id")
    }

    //quantity validation check
    private fun quantityValidation(quantity: String) {
        if (quantity.toInt() <= 0)
            updateCollectErrors(idError = "Enter a value greater than 0")
    }

    //Clear errors
    private fun clearErrors(field: String) {
        if (field == "id")
            _collectUiState.update { currentState ->
                currentState.copy(
                    idError = null,
                    collectUiError = false,
                    HTTPAuthError = null,
                    IOAuthError = null
                )
            }
        else
            _collectUiState.update { currentState ->
                currentState.copy(
                    idError = null,
                    collectUiError = false,
                    HTTPAuthError = null,
                    IOAuthError = null
                )
            }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TakaNiMaliApplication)
                val collectWasteRepository = application.container.collectWasteRepository
                CollectViewModel(collectWasteRepository = collectWasteRepository)
            }
        }
    }

}