package com.dca.takanimali.ui.report

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dca.takanimali.data.CollectWasteRepository
import com.dca.takanimali.data.ReportResource
import com.dca.takanimali.model.*
import com.dca.takanimali.ui.utils.initialWasteList
import com.dca.takanimali.ui.utils.initialZoneList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class ReportViewModel @Inject constructor(private val collectWasteRepository: CollectWasteRepository) :
    ViewModel() {

    var reportState: ReportResource by mutableStateOf(ReportResource.NotReported)
        private set

    var _reportUiState = MutableStateFlow(ReportUiState())
    val reportUiState = _reportUiState.asStateFlow()


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

    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    fun reportWaste(authToken: String?) {
        val token = "Bearer $authToken"

        Log.d("Report waste", "Report stage 1")

        _reportUiState.update { currentState ->
            currentState.copy(
                IOError = null,
                HTTPError = null
            )
        }

        uiScope.launch {
            Log.d("Report waste", "Report stage 2")
            reportState = ReportResource.Loading
            try {
                val userBody = collectWasteRepository.reportWaste(
                    selectedBlock.id,
                    selectedWaste.id,
                    selectedWasteType.id,
                    selectedZone.id,
                    token,

                    )
                reportState = ReportResource.Reported
                Log.d("Report waste", "Report stage 3")
            } catch (e: IOException) {
                Log.d("Report waste", "Report stage 4")
                _reportUiState.update { currentState ->
                    currentState.copy(
                        IOError = "Please check your internet connection"
                    )
                }
                reportState = ReportResource.NotReported
            } catch (e: HttpException) {
                Log.d("User auth err4", "${e.code()}")

               val errorMessage = if(e.code() == 400 )  {
                    "Waste sighting already reported"
                } else {
                    "Could not report! Contact admin for help"
                }
                _reportUiState.update { currentState ->
                    currentState.copy(
                        HTTPError = errorMessage
                    )
                }
                reportState = ReportResource.NotReported
            }
        }
    }

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

    fun updateReportState() {
        reportState = ReportResource.NotReported
    }
}