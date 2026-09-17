package com.dylankim.inventoryscanner.data.remote

import javax.inject.Inject

class ProductRemoteRepository @Inject constructor(
    private val productApi: ProductApi
) {
    suspend fun getProducts(): ProductResponse {
        return productApi.getProducts()
    }
}