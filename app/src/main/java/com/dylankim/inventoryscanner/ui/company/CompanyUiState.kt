package com.dylankim.inventoryscanner.ui.company

import com.dylankim.inventoryscanner.data.local.entity.Company
import com.dylankim.inventoryscanner.data.local.entity.Location

data class CompanyUiState(
    val companies: List<Company> = emptyList(),
    val selectedCompany: Company? = null,
    val locations: List<Location> = emptyList()
)
