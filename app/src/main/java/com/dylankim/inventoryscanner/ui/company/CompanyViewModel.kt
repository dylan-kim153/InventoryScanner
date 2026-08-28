package com.dylankim.inventoryscanner.ui.company

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dylankim.inventoryscanner.data.local.entity.Company
import com.dylankim.inventoryscanner.data.local.entity.Location
import com.dylankim.inventoryscanner.data.repository.CompanyRepository
import com.dylankim.inventoryscanner.data.repository.LocationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CompanyViewModel(
    private val companyRepository: CompanyRepository,
    private val locationRepository: LocationRepository
) : ViewModel() {
    private val _companies = MutableStateFlow<List<Company>>(emptyList())
    val companies: StateFlow<List<Company>> = _companies.asStateFlow()

    private val _selectedCompany = MutableStateFlow<Company?>(null)
    val selectedCompany = _selectedCompany.asStateFlow()

    private val _locations = MutableStateFlow<List<Location>>(emptyList())
    val locations = _locations.asStateFlow()

    fun loadCompanies(){
        viewModelScope.launch {
            _companies.value = companyRepository.getCompanies()
        }
    }

    fun selectCompany(company: Company){
        _selectedCompany.value = company

        loadLocations(company.id)
    }

    fun loadLocations(companyId: Long) {
        viewModelScope.launch {
            _locations.value = locationRepository.getLocationByCompanyId(companyId)
        }
    }

}