package com.dylankim.inventoryscanner.data.remote

data class ProductResponse(
    val products: List<ProductDto>,
    val total: Int,
    val skip: Int,
    val limit: Int
)