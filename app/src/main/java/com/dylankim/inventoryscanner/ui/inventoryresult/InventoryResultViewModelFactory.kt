package com.dylankim.inventoryscanner.ui.inventoryresult

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dylankim.inventoryscanner.data.repository.InventoryRecordRepository

class InventoryResultViewModelFactory(
    private val inventoryRecordRepository: InventoryRecordRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {
        if (modelClass.isAssignableFrom(InventoryResultViewModel::class.java)) {
            return InventoryResultViewModel(
                inventoryRecordRepository = inventoryRecordRepository
            ) as T
        }

        throw IllegalArgumentException(
            "Unknown ViewModel class: ${modelClass.name}"
        )
    }
}