package com.example.assignment

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.Data.Product
import com.example.assignment.Data.ProductAdapter
import com.example.assignment.viewmodels.ProductViewModel

class ProductListActivity : AppCompatActivity(), ProductAdapter.OnItemClickListener {

    private val viewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.products.observe(this) { products ->
            val adapter = ProductAdapter(products, this)
            recyclerView.adapter = adapter
        }

        viewModel.fetchProducts()
    }

    override fun onItemClick(product: Product) {
        val intent = Intent(this, ProductDetailActivity::class.java).apply {
            putExtra("product_title", product.title)
            putExtra("product_description", product.description)
            putExtra("product_price", product.price)
            putExtra("product_image", product.thumbnail)
        }
        startActivity(intent)
    }
}
