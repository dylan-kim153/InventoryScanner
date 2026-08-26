package com.dylankim.inventoryscanner.data.repository

import com.dylankim.inventoryscanner.data.local.dao.CompanyDao
import com.dylankim.inventoryscanner.data.local.entity.Company

class CompanyRepository(
    private val companyDao: CompanyDao
) {
    suspend fun getCompanies(): List<Company>{
        return companyDao.getAll()
    }

    suspend fun getCompanyCount(): Int{
        return companyDao.getCount()
    }
}