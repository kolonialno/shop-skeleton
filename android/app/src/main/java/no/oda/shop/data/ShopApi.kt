package no.oda.shop.data

import kotlinx.serialization.json.Json
import no.oda.shop.BuildConfig
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ShopApi {
    @GET("products")
    suspend fun products(): ProductsResponse

    @POST("orders")
    suspend fun placeOrder(@Body order: OrderRequest): OrderResponse

    companion object {
        fun create(): ShopApi {
            val client = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                .build()
            val json = Json { ignoreUnknownKeys = true }
            return Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .client(client)
                .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
                .build()
                .create(ShopApi::class.java)
        }
    }
}
