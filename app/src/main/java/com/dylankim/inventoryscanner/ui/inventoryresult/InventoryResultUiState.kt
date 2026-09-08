package com.dylankim.inventoryscanner.ui.inventoryresult

import com.dylankim.inventoryscanner.data.local.dto.LocationInventoryResult
import com.dylankim.inventoryscanner.data.local.entity.InventoryRecord

data class InventoryResultUiState(
    val totalQuantity: Double = 0.0,
    val locationResults: List<LocationInventoryResult> = emptyList(),
    val selectedLocationId: Long? = null,
    val selectedLocationNumber: String? = null,
    val detailRecords: List<InventoryRecord> = emptyList()
)
