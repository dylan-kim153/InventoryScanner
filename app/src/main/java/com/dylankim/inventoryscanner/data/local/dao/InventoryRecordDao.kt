package com.dylankim.inventoryscanner.data.local.dao

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.Query
import com.dylankim.inventoryscanner.data.local.entity.InventoryRecord

@Dao
interface InventoryRecordDao {

    //재고조사 기존 순번 조회
    @Query(
        """
               SELECT MAX(countingNumber)
               FROM InventoryRecord
               WHERE locationId = :locationId
               AND locationNumber = :locationNumber
               """
    )
    suspend fun getLastCountingNumber(
        locationId: Long,
        locationNumber: String
    ): Int?

    @Query("SELECT * FROM InventoryRecord")
    suspend fun getAll(): List<InventoryRecord>

    @Query("DELETE FROM InventoryRecord")
    suspend fun deleteAll()

    @Insert
    suspend fun insert(inventoryRecord: InventoryRecord): Long

}