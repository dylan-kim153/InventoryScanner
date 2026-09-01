package com.dylankim.inventoryscanner.ui.inventory

import com.dylankim.inventoryscanner.data.local.entity.Product

data class InventoryUiState(
    val companyId: Long = 0,
    val locationId: Long = 0,
    val locationNumber: String = "",
    val countingNumber: Int = 1,
    val product: Product? = null,
    val barcode: String = "",
    val quantity: String = ""
)