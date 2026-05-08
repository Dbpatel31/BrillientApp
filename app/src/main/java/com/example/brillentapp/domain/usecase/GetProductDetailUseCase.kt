package com.example.brillentapp.domain.usecase

import com.example.brillentapp.domain.model.Product
import com.example.brillentapp.domain.repository.ProductRepository
import com.example.brillentapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import javax.inject.Inject

class GetProductDetailUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(id: Int): Flow<Resource<Product>> = flow {
        try {
            emit(Resource.Loading())
            val product = repository.getProductDetail(id)
            emit(Resource.Success(product))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Error fetching details"))
        }
    }
}