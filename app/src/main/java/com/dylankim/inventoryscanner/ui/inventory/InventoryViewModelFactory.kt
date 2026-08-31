package com.dylankim.inventoryscanner.ui.inventory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dylankim.inventoryscanner.data.repository.InventoryRecordRepository
import com.dylankim.inventoryscanner.data.repository.ProductRepository

class InventoryViewModelFactory(
    private val inventoryRecordRepository: InventoryRecordRepository,
    private val productRepository: ProductRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {
        if (modelClass.isAssignableFrom(InventoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return InventoryViewModel(
                inventoryRecordRepository = inventoryRecordRepository,
                productRepository = productRepository
            ) as T

        }

        throw IllegalArgumentException(
            "Unknown ViewModel Class"
        )
    }
}
