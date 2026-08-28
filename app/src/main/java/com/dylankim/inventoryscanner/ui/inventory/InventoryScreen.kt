package com.dylankim.inventoryscanner.ui.inventory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun InventoryScreen(
    companyId: Long,
    locationId: Long,
    locationNumber: String,
    modifier: Modifier = Modifier
) {
    val viewmodel: InventoryViewModel = viewModel()
    val uiState by viewmodel.uiState.collectAsState()

    LaunchedEffect(companyId,locationId,locationNumber){
        viewmodel.initialize(
            companyId = companyId,
            locationId = locationId,
            locationNumber = locationNumber
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "업체: ${uiState.companyId}"
        )
        Text(
            text = "장소: ${uiState.locationId}"
        )
        Text(
            text = "위치: ${uiState.locationNumber}"
        )
        Text(
            text = "순번: ${uiState.countingNumber.toString().padStart(4,'0')}"
        )

    }
}