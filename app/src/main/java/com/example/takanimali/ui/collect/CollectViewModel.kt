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
import dagger.hilt.android.HiltAndroidApp
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
class CollectViewModel @Inject constructor(private val collectWasteRepository: CollectWasteRepository) :
    ViewModel() {
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

    //Collect waste
    fun collectWaste(leaderId: Int?, token: String?) {
        Log.d("Refresh function", "We are collecting")
        _collectUiState.update { currentState ->
            currentState.copy(
                collectNetworkError = false,
                HTTPAuthError = "",
                IOAuthError = ""
            )
        }
        val userId = collectFormState.value.userId
        val quantity = collectFormState.value.quantity
        var token = "Bearer $token"


        if (leaderId == null || leaderId == 0) {
            Log.d("Id Error", "We have an Id error")
            updateCollectErrors(idError = "User does not have permission to collect waste")
            return
        }

        Log.d("Refresh function", "We got to stage 2")

        userIdValidation(userId)
        quantityValidation(quantity)


        if (collectUiState.value.collectUiError) return



        Log.d("Refresh function", "We got to stage 3")

        viewModelScope.launch(Dispatchers.IO) {
            Log.d("Refresh function", "We got to stage 4")
            collectState = CollectResource.Loading
            try {
                val userBody = collectWasteRepository.collectWaste(
                    selectedBlock.id,
                    selectedLocation.id,
                    quantity.toFloat().toInt(),
                    leaderId,
                    userId,
                    selectedWaste.id,
                    selectedWasteType.id,
                    selectedZone.id,
                    token
                )
                Log.d("Collect successful", "${userBody.success}")
                Log.d("Refresh function", "We got to stage 5")
                collectFormState.value = collectFormState.value.copy(userId = "")
                collectFormState.value = collectFormState.value.copy(quantity = "")
                collectState = CollectResource.Collected
            } catch (e: IOException) {
                Log.d("Collect error", "Network failure ${e.message}")
                _collectUiState.update { currentState ->
                    currentState.copy(
                        IOAuthError = "Check your internet connection and try again",
                        collectNetworkError = true
                    )
                }
                collectState = CollectResource.NotCollected
            } catch (e: HttpException) {
                Log.d("Collect error", "${e.code()}")

                val errorMessage = when (e.code()) {
                    400 -> "Waste collection already exists"
                    422 -> "Please enter valid details including member id"
                    else -> "Could not collect! Try again later"
                }

                _collectUiState.update { currentState ->
                    currentState.copy(HTTPAuthError = errorMessage, collectNetworkError = true)
                }
                collectState = CollectResource.NotCollected
            }
        }
    }

    fun onUserIdChange(newUserId: String) {
        if (_collectUiState.value.quantityError != null)
            _collectUiState.value = _collectUiState.value.copy(idError = "", collectUiError = false)
        else
            clearErrors("id")
        collectFormState.value = collectFormState.value.copy(userId = newUserId)
    }

    fun onQuantityChange(newQuantity: String) {
        if (_collectUiState.value.idError != null)
            _collectUiState.value =
                _collectUiState.value.copy(quantityError = "", collectUiError = false)
        else
            clearErrors("quantity")
        collectFormState.value = collectFormState.value.copy(quantity = newQuantity)
    }

    fun updateCollectState() {
        collectState = CollectResource.NotCollected
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
        if (id.isBlank())
            updateCollectErrors(idError = "Please enter a valid user id")
    }

    //quantity validation check
    private fun quantityValidation(quantity: String) {
        try {
            if (quantity.toFloat() <= 0F)
                updateCollectErrors(quantityError = "Enter waste quantity to proceed")
        } catch (e: NumberFormatException) {
            updateCollectErrors(quantityError = "Waste quantity is invalid")
        }
    }

    //Clear errors
    private fun clearErrors(field: String) {
        if (field == "id")
            _collectUiState.update { currentState ->
                currentState.copy(
                    idError = "",
                    collectUiError = false,
                    HTTPAuthError = "",
                    IOAuthError = ""
                )
            }
        else
            _collectUiState.update { currentState ->
                currentState.copy(
                    quantityError = "",
                    collectUiError = false,
                    HTTPAuthError = "",
                    IOAuthError = ""
                )
            }
    }

}