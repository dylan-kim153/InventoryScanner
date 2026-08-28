package com.dylankim.inventoryscanner.ui.inventory

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class InventoryViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(InventoryUiState())
    val uiState: StateFlow<InventoryUiState> = _uiState.asStateFlow()

    fun initialize(
        companyId: Long,
        locationId: Long,
        locationNumber: String
    ){
        _uiState.value = _uiState.value.copy(
            companyId = companyId,
            locationId = locationId,
            locationNumber = locationNumber
        )
    }
}