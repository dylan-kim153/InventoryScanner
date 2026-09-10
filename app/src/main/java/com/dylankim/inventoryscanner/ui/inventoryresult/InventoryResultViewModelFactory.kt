package com.dylankim.inventoryscanner.ui.inventoryresult

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dylankim.inventoryscanner.data.repository.InventoryRecordRepository
import com.dylankim.inventoryscanner.data.repository.LocationRepository

class InventoryResultViewModelFactory(
    private val inventoryRecordRepository: InventoryRecordRepository,
    private val locationRepository: LocationRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {
        if (modelClass.isAssignableFrom(InventoryResultViewModel::class.java)) {
            return InventoryResultViewModel(
                inventoryRecordRepository = inventoryRecordRepository,
                locationRepository = locationRepository
            ) as T
        }

        throw IllegalArgumentException(
            "Unknown ViewModel class: ${modelClass.name}"
        )
    }
}