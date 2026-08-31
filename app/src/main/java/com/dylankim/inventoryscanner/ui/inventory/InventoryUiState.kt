package com.dylankim.inventoryscanner.ui.inventory

data class InventoryUiState(
    val companyId: Long = 0,
    val locationId: Long = 0,
    val locationNumber: String = "",
    val countingNumber: Int = 1
)