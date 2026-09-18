package com.dylankim.inventoryscanner.data.local.dao

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.Query
import com.dylankim.inventoryscanner.data.local.entity.Product

@Dao
interface ProductDao {
    @Query("SELECT * FROM product WHERE barcode = :barcode")
    suspend fun getProductByBarcode(barcode: String): Product?

    @Insert
    suspend fun insert(product: Product): Long

    @Insert
    suspend fun insertAll(products: List<Product>)

    @Query("DELETE FROM product WHERE companyId = :companyId")
    suspend fun deleteByCompanyId(companyId: Long)

}