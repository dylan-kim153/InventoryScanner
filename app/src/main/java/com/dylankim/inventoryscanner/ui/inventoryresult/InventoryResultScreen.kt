package com.dylankim.inventoryscanner.ui.inventoryresult

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun InventoryResultScreen(
    factory: InventoryResultViewModelFactory
) {
    val viewModel: InventoryResultViewModel = viewModel(
        factory = factory
    )

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

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
                text = "${result.locationId} / " +
                        "${result.locationNumber} / " +
                        "${result.totalQuantity}"
            )
        }
    }

}