package com.dylankim.inventoryscanner.data.local.dao

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.Query
import com.dylankim.inventoryscanner.data.local.entity.InventoryRecord

@Dao
interface InventoryRecordDao {
    @Query("SELECT * FROM InventoryRecord")
    suspend fun getAll(): List<InventoryRecord>

    @Query("DELETE FROM InventoryRecord")
    suspend fun deleteAll()

    @Insert
    suspend fun insert(inventoryRecord: InventoryRecord): Long

}