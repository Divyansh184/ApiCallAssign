package com.example.assignment.Data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assignment.R

class ProductAdapter(private val products: List<Product>, private val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_TITLE = 0
        private const val VIEW_TYPE_PRODUCT = 1
    }

    interface OnItemClickListener {
        fun onItemClick(product: Product)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) VIEW_TYPE_TITLE else VIEW_TYPE_PRODUCT
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_TITLE) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_title, parent, false)
            TitleViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
            ProductViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TitleViewHolder) {
            holder.bind()
        } else if (holder is ProductViewHolder) {
            holder.bind(products[position - 1], itemClickListener)
        }
    }

    override fun getItemCount(): Int {
        return products.size + 1
    }

    class TitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            (itemView as TextView).text = "Products List"
        }
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        private val ivProductImage: ImageView = itemView.findViewById(R.id.ivProductImage)

        fun bind(product: Product, clickListener: OnItemClickListener) {
            tvTitle.text = product.title
            tvDescription.text = product.description
            Glide.with(itemView.context).load(product.thumbnail).into(ivProductImage)
            itemView.setOnClickListener {
                clickListener.onItemClick(product)
            }
        }
    }
}
