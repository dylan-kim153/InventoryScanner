package com.dylankim.inventoryscanner.data.repository

import com.dylankim.inventoryscanner.data.local.dao.InventoryRecordDao
import com.dylankim.inventoryscanner.data.local.entity.InventoryRecord

class InventoryRecordRepository(
    private val inventoryRecordDao: InventoryRecordDao
) {
    suspend fun getLastCountingNumber(
        locationId: Long,
        locationNumber: String
    ): Int? {
        return inventoryRecordDao.getLastCountingNumber(
            locationId = locationId,
            locationNumber = locationNumber
        )
    }

    suspend fun getByLocation(
        locationId: Long,
        locationNumber: String
    ): List<InventoryRecord>{
        return inventoryRecordDao.getByLocation(
            locationId = locationId,
            locationNumber = locationNumber
        )
    }

    suspend fun insert(record: InventoryRecord){
        inventoryRecordDao.insert(record)
    }
}