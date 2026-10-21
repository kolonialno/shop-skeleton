package no.oda.shop.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: String,
    val title: String,
    val subtitle: String,
    val description: String,
    val image: String,
    @SerialName("price_ore") val priceOre: Int,
)

@Serializable
data class ProductsResponse(val products: List<Product>)

@Serializable
data class OrderLine(
    @SerialName("product_id") val productId: String,
    val quantity: Int,
)

@Serializable
data class OrderRequest(val items: List<OrderLine>)

@Serializable
data class OrderResponse(@SerialName("order_id") val orderId: Int)
