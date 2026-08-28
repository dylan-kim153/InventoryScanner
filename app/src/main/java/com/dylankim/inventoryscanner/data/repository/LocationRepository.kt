package com.dylankim.inventoryscanner.data.repository

import com.dylankim.inventoryscanner.data.local.dao.LocationDao
import com.dylankim.inventoryscanner.data.local.entity.Location

class LocationRepository(
    private val locationDao: LocationDao
) {
    suspend fun getLocationByCompanyId(companyId: Long): List<Location>{
        return locationDao.getLocationsByCompanyId(companyId)
    }
}
