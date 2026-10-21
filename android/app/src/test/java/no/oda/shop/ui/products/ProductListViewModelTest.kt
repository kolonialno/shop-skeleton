package no.oda.shop.ui.products

import no.oda.shop.MainDispatcherRule
import no.oda.shop.data.OrderRequest
import no.oda.shop.data.OrderResponse
import no.oda.shop.data.Product
import no.oda.shop.data.ProductsResponse
import no.oda.shop.data.ShopApi
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class ProductListViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val bananas = Product("bananer", "Bananer i Klase", "5-6 stk", "", "", 2690)

    private class FakeShopApi(private val products: List<Product>) : ShopApi {
        override suspend fun products() = ProductsResponse(products)

        override suspend fun placeOrder(order: OrderRequest) = OrderResponse(orderId = 1)
    }

    @Test
    fun `shows the products the API returns`() {
        val viewModel = ProductListViewModel(FakeShopApi(listOf(bananas)))

        assertEquals(listOf(bananas), viewModel.uiState.value.products)
    }
}
