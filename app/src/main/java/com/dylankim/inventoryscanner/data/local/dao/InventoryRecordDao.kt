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

    @Query(
        """
            SELECT * FROM InventoryRecord
            WHERE locationId = :locationId
              AND locationNumber = :locationNumber
            ORDER BY countingNumber DESC
            """
    )
    suspend fun getByLocation(
        locationId: Long,
        locationNumber: String
    ): List<InventoryRecord>

    @Query("DELETE FROM InventoryRecord")
    suspend fun deleteAll()

    @Query("DELETE FROM InventoryRecord WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query(
        """
        UPDATE InventoryRecord
           SET quantity = :quantity,
               updatedAt = :updatedAt
        WHERE id = :id
        """
    )
    suspend fun updateQuantity(
        id: Long,
        quantity: String,
        updatedAt: String
    )

    @Insert
    suspend fun insert(inventoryRecord: InventoryRecord): Long

}