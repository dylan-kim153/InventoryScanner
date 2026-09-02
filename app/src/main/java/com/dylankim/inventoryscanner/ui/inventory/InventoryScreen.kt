package com.dylankim.inventoryscanner.ui.inventory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlin.math.sin

@Composable
fun InventoryScreen(
    companyId: Long,
    locationId: Long,
    locationNumber: String,
    factory: InventoryViewModelFactory,
    modifier: Modifier = Modifier
) {
    val viewModel: InventoryViewModel = viewModel(
        factory = factory
    )
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(companyId,locationId,locationNumber){
        viewModel.initialize(
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

        OutlinedTextField(
            value = uiState.barcode,
            onValueChange = viewModel::updateBarcode,
            label = { Text("바코드")},
            singleLine = true
        )

        Button(
            onClick = {
                viewModel.findProduct(uiState.barcode)
            },
            enabled = uiState.barcode.isNotBlank()
        ) {
            Text("상품 조회")
        }

        uiState.product?.let { product ->
            Text(
                text = "상품명 : ${product.name}"
            )
            Text(
                text = "가격 : ${product.price}"
            )
        } ?: run {
            if (uiState.barcode.isNotBlank()) {
                Text(
                    text = "미등록상품"
                )
            }
        }

        OutlinedTextField(
            value = uiState.quantity,
            onValueChange = viewModel::updateQuantity,
            label = { Text("수량")},
            singleLine = true
        )

        Button(
            onClick = {
                uiState.product?.let { product ->
                    viewModel.saveInventoryRecord(
                        barcode = uiState.barcode,
                        productCode = product.productCode,
                        productName = product.name,
                        price = product.price,
                        quantity = uiState.quantity
                    )

                }
            },
            enabled = uiState.product != null &&
                uiState.quantity.isNotBlank()
        ) {
            Text("저장")
        }

        Text(
            text = "재고조사 내역"
        )

        uiState.inventoryRecords.forEach { record ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "${record.countingNumber.toString().padStart(4,'0')} / " +
                            "${record.productName} / " +
                            "수량 : ${record.quantity}"
                )

                Button(
                    onClick = {
                        viewModel.deleteInventoryRecord(record.id)
                    }
                ) {
                    Text("삭제")
                }
            }
        }
    }
}