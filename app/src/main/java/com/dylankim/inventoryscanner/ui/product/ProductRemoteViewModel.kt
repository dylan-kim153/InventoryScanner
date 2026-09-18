package com.dylankim.inventoryscanner.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dylankim.inventoryscanner.data.remote.ProductDto
import com.dylankim.inventoryscanner.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProductRemoteUiState(
    val products: List<ProductDto> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val savedCount: Int = 0
)

@HiltViewModel
class ProductRemoteViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductRemoteUiState())
    val uiState: StateFlow<ProductRemoteUiState> = _uiState.asStateFlow()

    fun loadProducts(companyId: Long) {
        viewModelScope.launch {
            _uiState.value = ProductRemoteUiState(isLoading = true)

            try {
                val response = repository.getProducts()

                repository.downloadProducts(companyId)

                _uiState.value = ProductRemoteUiState(
                    products = response.products,
                    savedCount = response.products.size
                )
            } catch (e: Exception) {
                _uiState.value = ProductRemoteUiState(
                    errorMessage = e.message ?: "상품 정보를 불러오지 못했습니다."
                )
            }
        }
    }
}