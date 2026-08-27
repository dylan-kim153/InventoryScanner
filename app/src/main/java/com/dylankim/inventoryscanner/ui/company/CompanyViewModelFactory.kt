package com.dylankim.inventoryscanner.ui.company

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dylankim.inventoryscanner.data.repository.CompanyRepository
import com.dylankim.inventoryscanner.data.repository.LocationRepository

class CompanyViewModelFactory(
    private val companyRepository: CompanyRepository,
    private val locationRepository: LocationRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CompanyViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CompanyViewModel(
                companyRepository,
                locationRepository
            ) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel class")
    }
}