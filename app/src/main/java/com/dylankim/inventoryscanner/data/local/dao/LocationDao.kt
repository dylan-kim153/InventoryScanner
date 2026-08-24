package com.dylankim.inventoryscanner.data.local.dao

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.Query
import com.dylankim.inventoryscanner.data.local.entity.Location

@Dao
interface LocationDao {
    @Query("SELECT * FROM location where companyId = :companyId")
    suspend fun getLocationsByCompanyId(companyId: Long): List<Location>

    @Insert
    suspend fun insert(location: Location): Long
}