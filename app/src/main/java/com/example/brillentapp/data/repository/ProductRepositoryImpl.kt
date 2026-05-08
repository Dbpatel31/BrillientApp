package com.example.brillentapp.data.repository

import com.example.brillentapp.data.remote.ApiService
import com.example.brillentapp.data.remote.toDomain
import com.example.brillentapp.domain.model.Product
import com.example.brillentapp.domain.repository.ProductRepository
import javax.inject.Inject



import com.example.brillentapp.data.local.dao.ProductDao
import com.example.brillentapp.data.local.entity.ProductEntity


import com.example.brillentapp.data.remote.toDomain
import com.example.brillentapp.data.remote.toEntity

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class ProductRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val dao: ProductDao // DAO inject karyu
) : ProductRepository {

    override suspend fun getProducts(): List<Product> {
        // Step 1: Check karo ke local database ma data che ke nahi
        val localProducts = dao.getAllProducts()


        return if (localProducts.isNotEmpty()) {

            localProducts.map { it.toDomain() }
        } else {
            // Jo database khali hoy to API call karo, save karo ane return karo
            val remoteProducts = api.getProducts()
            dao.insertProducts(remoteProducts.map { it.toEntity() })
            remoteProducts.map { it.toDomain() }
        }
    }

    override suspend fun getProductDetail(id: Int): Product {
        // Detail mate pan pela local check kari shakay
        val localProduct = dao.getProductById(id)
        return localProduct?.toDomain() ?: api.getProductDetail(id).toDomain().also {
            // Optional: API mathi ave to database update kari sako
        }
    }
}