package com.dylankim.inventoryscanner.ui.company

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dylankim.inventoryscanner.data.repository.CompanyRepository

class CompanyViewModelFactory(
    private val companyRepository: CompanyRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CompanyViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CompanyViewModel(companyRepository) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel class")
    }
}