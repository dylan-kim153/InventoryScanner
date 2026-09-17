package com.dylankim.inventoryscanner.data.remote

data class ProductDto(
    val id: Int,
    val title: String,
    val price: Double,
    val meta: ProductMetaDto
)

data class ProductMetaDto(
    val barcode: String
)