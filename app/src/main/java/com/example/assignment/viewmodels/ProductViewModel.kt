package com.example.assignment.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignment.Data.Product
import com.example.assignment.Data.ProductResponse
import com.example.assignment.Data.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductViewModel : ViewModel() {
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    init {
        fetchProducts()
    }

    fun fetchProducts() {
        RetrofitClient.instance.getProducts().enqueue(object : Callback<ProductResponse> {
            override fun onResponse(call: Call<ProductResponse>, response: Response<ProductResponse>) {
                if (response.isSuccessful) {
                    _products.postValue(response.body()?.products)
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {

            }
        })
    }
}
