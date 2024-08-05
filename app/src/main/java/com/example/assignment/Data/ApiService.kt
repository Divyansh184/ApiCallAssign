package  com.example.assignment.Data

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    fun getProducts(): Call<ProductResponse>
}

data class ProductResponse(val products: List<Product>)
