package com.dylankim.inventoryscanner.ui.inventory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dylankim.inventoryscanner.data.local.entity.InventoryRecord
import com.dylankim.inventoryscanner.data.repository.CompanyRepository
import com.dylankim.inventoryscanner.data.repository.InventoryRecordRepository
import com.dylankim.inventoryscanner.data.repository.LocationRepository
import com.dylankim.inventoryscanner.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class InventoryViewModel(
    private val inventoryRecordRepository: InventoryRecordRepository,
    private val productRepository: ProductRepository,
    private val companyRepository: CompanyRepository,
    private val locationRepository: LocationRepository
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
                (lastCountingNumber ?: 0) + 1

            val inventoryRecords =
                inventoryRecordRepository.getByLocation(
                    locationId = locationId,
                    locationNumber = locationNumber
                )

            val company = companyRepository.getCompanyById(companyId)
            val location = locationRepository.getLocationById(locationId)

            _uiState.value = _uiState.value.copy(
                companyId = companyId,
                companyName = company?.name ?: "",
                locationId = locationId,
                locationName = location?.name ?: "",
                locationNumber = locationNumber,
                countingNumber = nextCountingNumber,
                inventoryRecords = inventoryRecords
            )
        }
    }

    fun saveInventoryRecord(
        barcode: String,
        productCode: String,
        productName: String,
        price: String,
        quantity: String
    ) {
        viewModelScope.launch {
            val state = _uiState.value
            val now = LocalDateTime.now().toString()

            val record = InventoryRecord(
                locationId = state.locationId,
                locationNumber = state.locationNumber,
                countingNumber = state.countingNumber,
                barcode = barcode,
                productCode = productCode,
                productName = productName,
                price = price,
                quantity = quantity,
                createdAt = now,
                updatedAt = now
            )
            //데이터 갱신
            inventoryRecordRepository.insert(record)

            //목록갱신
            val inventoryRecords =
                inventoryRecordRepository.getByLocation(
                    locationId = state.locationId,
                    locationNumber = state.locationNumber
                )

            //순번 +1
            _uiState.value = _uiState.value.copy(
                countingNumber = state.countingNumber + 1,
                inventoryRecords = inventoryRecords,
                barcode = "",
                product = null,
                quantity = ""
            )
        }
    }

    fun findProduct(barcode: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                product = null,
                productError = null
            )

            val product = productRepository.getProductByBarcode(barcode)

            _uiState.value = _uiState.value.copy(
                product = product,
                productError = if (product == null) {
                    "등록되지 않은 상품입니다."
                } else {
                    null
                }
            )
        }
    }

    fun updateBarcode(barcode: String) {
        _uiState.value = _uiState.value.copy(
            barcode = barcode
        )
    }

    fun updateQuantity(quantity: String) {
        _uiState.value = _uiState.value.copy(
            quantity = quantity
        )
    }

    fun deleteInventoryRecord(id: Long){
        viewModelScope.launch {
            inventoryRecordRepository.deleteById(id)

            val state = _uiState.value

            val inventoryRecords =
                inventoryRecordRepository.getByLocation(
                    locationId = state.locationId,
                    locationNumber = state.locationNumber
                )

            _uiState.value = _uiState.value.copy(
                inventoryRecords = inventoryRecords
            )
        }
    }

    fun updateInventoryRecord(
        id: Long,
        quantity: String
    ) {
        viewModelScope.launch {
            val state = _uiState.value
            val now = LocalDateTime.now().toString()

            inventoryRecordRepository.updateQuantity(
                id = id,
                quantity = quantity,
                updatedAt = now
            )

            val inventoryRecords =
                inventoryRecordRepository.getByLocation(
                    locationId = state.locationId,
                    locationNumber = state.locationNumber
                )

            _uiState.value = state.copy(
                inventoryRecords = inventoryRecords
            )
        }
    }


}