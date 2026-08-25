package com.dylankim.inventoryscanner.data.local.dao

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.Query
import com.dylankim.inventoryscanner.data.local.entity.Company

@Dao
interface CompanyDao {
    @Query("SELECT * FROM company")
    suspend fun getAll(): List<Company>

    @Query("SELECT COUNT(*) FROM company")
    suspend fun getCount(): Int

    @Insert
    suspend fun insert(company: Company): Long

}