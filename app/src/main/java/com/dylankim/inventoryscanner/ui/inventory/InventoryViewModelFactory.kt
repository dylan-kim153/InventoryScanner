package com.dylankim.inventoryscanner.ui.inventory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dylankim.inventoryscanner.data.repository.InventoryRecordRepository

class InventoryViewModelFactory(
    private val inventoryRecordRepository: InventoryRecordRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {
        if (modelClass.isAssignableFrom(InventoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return InventoryViewModel(
                inventoryRecordRepository = inventoryRecordRepository
            ) as T

        }

        throw IllegalArgumentException(
            "Unknown ViewModel Class"
        )
    }
}
