package com.dylankim.inventoryscanner.data.remote

import retrofit2.http.GET

interface ProductApi {

    @GET("products")
    suspend fun getProducts(): ProductResponse
}