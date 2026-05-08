package com.example.brillentapp.domain.usecase

import com.example.brillentapp.domain.model.Product
import com.example.brillentapp.domain.repository.ProductRepository
import com.example.brillentapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(query: String = ""): Flow<Resource<List<Product>>> = flow {
        try {
            emit(Resource.Loading())
            val products = repository.getProducts()

            // Search logic: Jo query hoy to filter karo
            val filteredProducts = if (query.isEmpty()) {
                products
            } else {
                products.filter {
                    it.title.contains(query, ignoreCase = true) ||
                            it.category.contains(query, ignoreCase = true)
                }
            }
            emit(Resource.Success(filteredProducts))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}