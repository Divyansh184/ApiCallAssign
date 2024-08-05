package com.example.assignment

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide

class ProductDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Product Detail"

        val productTitle = intent.getStringExtra("product_title")
        val productDescription = intent.getStringExtra("product_description")
        val productPrice = intent.getDoubleExtra("product_price", 0.0)
        val productImage = intent.getStringExtra("product_image")

        val tvProductTitle = findViewById<TextView>(R.id.tvProductTitle)
        val tvProductDescription = findViewById<TextView>(R.id.tvProductDescription)
        val tvProductPrice = findViewById<TextView>(R.id.tvProductPrice)
        val ivProductImage = findViewById<ImageView>(R.id.ivProductImage)

        tvProductTitle.text = productTitle
        tvProductDescription.text = productDescription
        tvProductPrice.text = "Price: $$productPrice"
        Glide.with(this).load(productImage).into(ivProductImage)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
