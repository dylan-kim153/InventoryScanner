package com.dylankim.inventoryscanner.ui.inventoryresult

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dylankim.inventoryscanner.data.repository.InventoryRecordRepository
import com.dylankim.inventoryscanner.data.repository.LocationRepository
import com.dylankim.inventoryscanner.ui.inventory.InventoryUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class InventoryResultViewModel(
    private val inventoryRecordRepository: InventoryRecordRepository,
    private val locationRepository: LocationRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(InventoryResultUiState())
    val uiState: StateFlow<InventoryResultUiState> = _uiState.asStateFlow()

    fun loadResult() {
        viewModelScope.launch {
            val totalQuantity = inventoryRecordRepository.getTotalQuantity()

            val locationResults = inventoryRecordRepository.getQuantityByLocationNumber()

            _uiState.value = InventoryResultUiState(
                totalQuantity = totalQuantity,
                locationResults = locationResults
            )
        }
    }

    fun selectLocation(
        locationId: Long,
        locationNumber: String
    ){
        viewModelScope.launch{

            val location =
                locationRepository.getLocationById(locationId)

            val detailRecords = inventoryRecordRepository.getByLocation(
                locationId = locationId,
                locationNumber = locationNumber,
            )

            _uiState.update {
                it.copy(
                    selectedLocationId = locationId,
                    selectedLocationName = location?.name ?: "",
                    selectedLocationNumber = locationNumber,
                    detailRecords = detailRecords
                )
            }
        }
    }
}