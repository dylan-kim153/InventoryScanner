package com.dylankim.inventoryscanner.ui.inventory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dylankim.inventoryscanner.data.local.entity.InventoryRecord
import com.dylankim.inventoryscanner.data.repository.InventoryRecordRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class InventoryViewModel(
    private val inventoryRecordRepository: InventoryRecordRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(InventoryUiState())
    val uiState: StateFlow<InventoryUiState> = _uiState.asStateFlow()

    fun initialize(
        companyId: Long,
        locationId: Long,
        locationNumber: String
    ) {
        viewModelScope.launch {

            //마지막 재고조사 순번 구하기
            val lastCountingNumber =
                inventoryRecordRepository.getLastCountingNumber(
                    locationId = locationId,
                    locationNumber = locationNumber
                )

            val nextCountingNumber =
                (lastCountingNumber ?: 0 ) + 1

            _uiState.value = _uiState.value.copy(
                companyId = companyId,
                locationId = locationId,
                locationNumber = locationNumber,
                countingNumber = nextCountingNumber
            )
        }
    }

    fun saveInventoryRecord(
        barcode: String,
        productCode: String,
        productName: String,
        price: String,
        quantity: String
    ){
        viewModelScope.launch {
            val state = _uiState.value

            var record = InventoryRecord(
                locationId = state.locationId,
                locationNumber = state.locationNumber,
                countingNumber = state.countingNumber,
                barcode = barcode,
                productCode = productCode,
                productName = productName,
                price = price,
                quantity = quantity,
                createdAt = LocalDateTime.now().toString()
            )

            inventoryRecordRepository.insert(record)

        }
    }
}