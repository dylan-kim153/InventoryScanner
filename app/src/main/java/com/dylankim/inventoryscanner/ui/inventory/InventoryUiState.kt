package com.dylankim.inventoryscanner.ui.inventory

import com.dylankim.inventoryscanner.data.local.entity.InventoryRecord
import com.dylankim.inventoryscanner.data.local.entity.Product

data class InventoryUiState(
    val companyId: Long = 0,
    val companyName: String = "",
    val locationId: Long = 0,
    val locationName: String = "",
    val locationNumber: String = "",
    val countingNumber: Int = 1,
    val product: Product? = null,
    val barcode: String = "",
    val quantity: String = "",
    val inventoryRecords: List<InventoryRecord> = emptyList()
)