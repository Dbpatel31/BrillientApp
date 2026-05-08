package com.example.brillentapp.presentation.product_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
// AA IMPORT SACHO CHE
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.brillentapp.R
import com.example.brillentapp.domain.model.Product

class ProductAdapter(private val onItemClick: (Product) -> Unit) :
    ListAdapter<Product, ProductAdapter.ProductViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position) // Have red line jati rehse
        holder.bind(product)
        holder.itemView.setOnClickListener { onItemClick(product) }
    }

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val ivProduct: ImageView = view.findViewById(R.id.ivProduct)
        private val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        private val tvPrice: TextView = view.findViewById(R.id.tvPrice)

        fun bind(product: Product) {
            tvTitle.text = product.title
            tvPrice.text = "$${product.price}"
            Glide.with(itemView.context).load(product.image).into(ivProduct)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Product, newItem: Product) = oldItem == newItem
    }
}