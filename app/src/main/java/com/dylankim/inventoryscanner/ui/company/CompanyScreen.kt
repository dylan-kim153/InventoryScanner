package com.dylankim.inventoryscanner.ui.company

import android.R.attr.enabled
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.dylankim.inventoryscanner.data.local.entity.Company

@Composable
fun CompanyScreen(
    factory: CompanyViewModelFactory,
    navController: NavController,
    modifier: Modifier = Modifier
) {

    val viewModel: CompanyViewModel = viewModel(
        factory = factory
    )

    val uiState by viewModel.uiState.collectAsState()

    val companies = uiState.companies
    val locations = uiState.locations

    LaunchedEffect(Unit) {
        viewModel.loadCompanies()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        companies.forEach { company ->
            Text(
                text = company.name,
                modifier = Modifier.clickable{
                    viewModel.selectCompany(company)
                }
            )
        }
        locations.forEach { location ->
            Text(
                text = location.name,
                modifier = Modifier.clickable{
                    viewModel.selectLocation(location = location)
                }
            )
        }
        uiState.selectedLocation?.let { location ->
            Text(
                text = "선택된 Location : ${location.name}"
            )

            OutlinedTextField(
                value = uiState.locationNumber,
                onValueChange = viewModel::updateLocationNumber,
                label = {Text("LocationNumber")},
                singleLine = true
            )

            Button(
                onClick = {
                    val companyId = uiState.selectedCompany?.id ?: return@Button
                    val locationId = uiState.selectedLocation?.id ?: return@Button
                    val locationNumber = uiState.locationNumber

                    navController.navigate(
                        "inventory/$companyId/$locationId/$locationNumber"
                    )
                },
                enabled = uiState.selectedCompany != null &&
                        uiState.selectedLocation != null &&
                        uiState.locationNumber.isNotBlank()
            ){
                Text("재고조사 시작")
            }



        }

        Button(
            onClick = {

                val companyId = uiState.selectedCompany?.id ?: return@Button

                navController.navigate("inventoryresult/$companyId")

            },
            enabled = uiState.selectedCompany != null
        ) {
            Text("재고조사 결과")
        }
    }
}