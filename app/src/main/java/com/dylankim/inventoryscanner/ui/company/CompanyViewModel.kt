package com.dylankim.inventoryscanner.ui.company

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dylankim.inventoryscanner.data.local.entity.Company
import com.dylankim.inventoryscanner.data.repository.CompanyRepository
import com.dylankim.inventoryscanner.data.repository.LocationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CompanyViewModel(
    private val companyRepository: CompanyRepository,
    private val locationRepository: LocationRepository
) : ViewModel() {
    private val _companies = MutableStateFlow<List<Company>>(emptyList())
    val companies: StateFlow<List<Company>> = _companies

    fun loadCompanies(){
        viewModelScope.launch {
            _companies.value = companyRepository.getCompanies()
        }
    }

    fun loadLocations(companyId: Long) {
        viewModelScope.launch {
            val locations = locationRepository.getLocationByCompanyId(companyId)

            Log.d(
                "CompanyViewModel",
                "locations = $locations"
            )
        }
    }

}