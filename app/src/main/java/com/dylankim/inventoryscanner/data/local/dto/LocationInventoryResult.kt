package com.dylankim.inventoryscanner.data.local.dto

data class LocationInventoryResult(
    val locationId: Long,
    val locationNumber: String,
    val totalQuantity: Double
)
