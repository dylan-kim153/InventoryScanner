package com.dylankim.inventoryscanner.ui.company

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dylankim.inventoryscanner.data.local.entity.Company
import com.dylankim.inventoryscanner.data.repository.CompanyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CompanyViewModel(
    private val companyRepository: CompanyRepository
) : ViewModel() {
    private val _companies = MutableStateFlow<List<Company>>(emptyList())
    val companies: StateFlow<List<Company>> = _companies

    fun loadCompanies(){
        viewModelScope.launch {
            _companies.value = companyRepository.getCompanies()
        }
    }
}