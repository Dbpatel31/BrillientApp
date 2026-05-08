package com.example.brillentapp.domain.repository

import com.example.brillentapp.domain.model.Product

interface ProductRepository {
    suspend fun getProducts(): List<Product>
    suspend fun getProductDetail(id: Int): Product
}