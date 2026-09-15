package com.dylankim.inventoryscanner.data.repository

import com.dylankim.inventoryscanner.data.local.dao.CompanyDao
import com.dylankim.inventoryscanner.data.local.entity.Company
import javax.inject.Inject

class CompanyRepository @Inject constructor(
    private val companyDao: CompanyDao
) {
    suspend fun getCompanies(): List<Company>{
        return companyDao.getAll()
    }

    suspend fun getCompanyById(companyId: Long): Company? {
        return companyDao.getById(companyId)
    }

    suspend fun getCompanyCount(): Int{
        return companyDao.getCount()
    }
}