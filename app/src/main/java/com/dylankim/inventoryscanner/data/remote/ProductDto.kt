package com.dylankim.inventoryscanner.data.remote

data class ProductDto(
    val id: Int,
    val title: String,
    val price: Double,
    val barcode: String
)