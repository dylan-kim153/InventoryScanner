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

    private val _uiState = MutableStateFlow(CompanyUiState())
    val uiState: StateFlow<CompanyUiState> = _uiState.asStateFlow()

    fun loadCompanies(){
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                companies = companyRepository.getCompanies()
            )
        }
    }

    fun selectCompany(company: Company){
        _uiState.value = _uiState.value.copy(
            selectedCompany = company
        )

        loadLocations(company.id)
    }

    fun selectLocation(location: Location) {
        _uiState.value = _uiState.value.copy(
            selectedLocation = location
        )
    }

    fun updateLocationNumber(locationNumber: String){
        _uiState.value = _uiState.value.copy(
            locationNumber = locationNumber
        )
    }

    fun loadLocations(companyId: Long) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                locations = locationRepository.getLocationByCompanyId(companyId)
            )
        }
    }

}