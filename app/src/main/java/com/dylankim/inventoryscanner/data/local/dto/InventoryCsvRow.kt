package com.dylankim.inventoryscanner.data.local.dto

data class InventoryCsvRow(
    val barcode: String,
    val productName: String,
    val quantity: String,
    val price: String,
    val countingNumber: Int,
    val locationName: String,
    val locationNumber: String,
    val createdAt: String
)
