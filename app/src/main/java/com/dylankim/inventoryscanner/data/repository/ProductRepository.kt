package com.dylankim.inventoryscanner.data.repository

import com.dylankim.inventoryscanner.data.local.dao.ProductDao
import com.dylankim.inventoryscanner.data.local.entity.Product
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productDao: ProductDao
) {
    suspend fun getProductByBarcode(barcode: String): Product? {
        return productDao.getProductByBarcode(barcode)
    }

}