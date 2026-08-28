package com.dylankim.inventoryscanner.data.repository

import com.dylankim.inventoryscanner.data.local.dao.InventoryRecordDao

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
}