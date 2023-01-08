package com.example.takanimali.ui.report

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.takanimali.TakaNiMaliApplication
import com.example.takanimali.data.CollectWasteRepository
import com.example.takanimali.data.RegisterResource
import com.example.takanimali.data.ReportResource
import com.example.takanimali.model.*
import com.example.takanimali.ui.utils.initialWasteList
import com.example.takanimali.ui.utils.initialZoneList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class ReportViewModel @Inject constructor (private val collectWasteRepository: CollectWasteRepository) : ViewModel() {

    var reportState: ReportResource by mutableStateOf(ReportResource.NotReported)
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

    fun reportWaste(authToken: String?) {
        val token = "Bearer $authToken"

        viewModelScope.launch {
            reportState = ReportResource.Loading
            try {
                val userBody = collectWasteRepository.reportWaste(
                    selectedBlock.id,
                    selectedWaste.id,
                    selectedWasteType.id,
                    selectedZone.id,
                    token,

                )
                Log.d("User auth Token", "${userBody.message}")
                reportState = ReportResource.Reported
            } catch (e: IOException) {
                Log.d("User auth err1", "Network failure ${e.message}")
               reportState = ReportResource.NotReported
            } catch (e: HttpException) {
                Log.d("User auth err4", "${e.code()}")
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