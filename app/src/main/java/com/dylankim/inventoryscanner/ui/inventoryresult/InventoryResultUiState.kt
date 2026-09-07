package com.dylankim.inventoryscanner.ui.inventoryresult

import com.dylankim.inventoryscanner.data.local.dto.LocationInventoryResult

data class InventoryResultUiState(
    val totalQuantity: Double = 0.0,
    val locationResults: List<LocationInventoryResult> = emptyList()
)
