package com.dylankim.inventoryscanner.data.repository

import com.dylankim.inventoryscanner.data.local.dao.ProductDao
import com.dylankim.inventoryscanner.data.local.entity.Product
import com.dylankim.inventoryscanner.data.remote.ProductRemoteRepository
import com.dylankim.inventoryscanner.data.remote.ProductResponse
import com.dylankim.inventoryscanner.data.remote.toEntity
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productDao: ProductDao,
    private val productRemoteRepository: ProductRemoteRepository
) {
    suspend fun getProductByBarcode(barcode: String): Product? {
        return productDao.getProductByBarcode(barcode)
    }

    suspend fun insertProducts(products: List<Product>) {
        productDao.insertAll(products)
    }

    suspend fun getProducts(): ProductResponse {
        return productRemoteRepository.getProducts()
    }

    suspend fun downloadProducts(companyId: Long) {
        val response = productRemoteRepository.getProducts()

        val products = response.products.map { productDto ->
            productDto.toEntity(companyId)
        }

        productDao.deleteByCompanyId(companyId)
        productDao.insertAll(products)
    }

}