package no.oda.shop.ui.products

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import no.oda.shop.data.Product
import no.oda.shop.data.ShopApi
import retrofit2.HttpException
import java.io.IOException

data class ProductListUiState(
    val products: List<Product> = emptyList(),
)

sealed interface ProductListEvent {
    // TODO: The events the screen sends, like adding a product to the cart.
}

class ProductListViewModel(private val api: ShopApi) : ViewModel() {
    private val _uiState = MutableStateFlow(ProductListUiState())
    val uiState: StateFlow<ProductListUiState> = _uiState.asStateFlow()

    init {
        loadProducts()
    }

    fun onEvent(event: ProductListEvent) {
        // TODO: The cart state, and adding, changing a quantity and buying.
    }

    private fun loadProducts() {
        viewModelScope.launch {
            try {
                val products = api.products().products
                _uiState.update { it.copy(products = products) }
            } catch (exception: IOException) {
                Log.e("ProductListViewModel", "GET /api/products failed", exception)
            } catch (exception: HttpException) {
                Log.e("ProductListViewModel", "GET /api/products failed", exception)
            }
        }
    }
}
