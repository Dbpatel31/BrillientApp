package com.example.brillentapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("products")
    suspend fun getProducts(): List<ProductDto>

    @GET("products/{id}")
    suspend fun getProductDetail(@Path("id") id: Int): ProductDto
}