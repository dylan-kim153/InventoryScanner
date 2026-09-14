package com.dylankim.inventoryscanner.ui.inventoryresult

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.dylankim.inventoryscanner.data.export.CsvFileWriter
import com.dylankim.inventoryscanner.data.export.CsvGenerator

@Composable
fun InventoryResultScreen(
    companyId: Long,
    factory: InventoryResultViewModelFactory,
    navController: NavController
) {
    val viewModel: InventoryResultViewModel = viewModel(
        factory = factory
    )
    val context = LocalContext.current

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val csvFileLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument("text/csv")
    ) { uri ->
        if (uri != null) {
            val csv = CsvGenerator.generate(uiState.csvRows)

            CsvFileWriter(context).write(
                uri = uri,
                csv = csv
            )
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadResult()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "재고조사 결과")

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "전체 조사 수량 : ${uiState.totalQuantity}")

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Location별 조사 결과")

        uiState.locationResults.forEach { result ->
            Text(
                text = "${result.locationName} / " +
                        "${result.locationNumber} / " +
                        "${result.totalQuantity}",
                modifier = Modifier.clickable{
                    viewModel.selectLocation(
                        locationId = result.locationId,
                        locationNumber = result.locationNumber
                    )
                }
            )
        }

        if(uiState.selectedLocationId != null &&
            uiState.selectedLocationNumber != null){
            Spacer(modifier = Modifier.height(24.dp))

            Text( text = "선택한 Location : " +
                        "${uiState.selectedLocationName} / " +
                        "${uiState.selectedLocationNumber}"
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(text = "상세 재고조사")

            uiState.detailRecords.forEach { record ->
                Text(
                    text = "${record.countingNumber} / " +
                            "${record.productName} / " +
                            "${record.quantity}"
                )
            }

            Button(
                onClick = {
                    navController.navigate(
                        "inventory/" +
                                "${companyId}/" +
                                "${uiState.selectedLocationId}/" +
                                "${uiState.selectedLocationNumber}"
                    )
                }
            ) {
                Text("재고조사 이동")
            }
        }

        Button(
            onClick = {
                csvFileLauncher.launch(
                    "재고조사_${System.currentTimeMillis()}.csv"
                )
            }
        ) {
            Text("CSV 내보내기")
        }
    }

}