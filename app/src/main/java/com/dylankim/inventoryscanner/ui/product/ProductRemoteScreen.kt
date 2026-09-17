package com.dylankim.inventoryscanner.ui.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun ProductRemoteScreen(
    viewModel: ProductRemoteViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadProducts()
    }

    when {
        uiState.isLoading -> {
            CircularProgressIndicator()
        }

        uiState.errorMessage != null -> {
            Text(
                text = uiState.errorMessage ?: "알 수 없는 오류"
            )
        }

        else -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(uiState.products) { product ->
                    Column {
                        Text(text = product.title)
                        Text(text = "가격: ${product.price}")
                        Text(text = "바코드: ${product.barcode}")
                    }
                }
            }
        }
    }
}