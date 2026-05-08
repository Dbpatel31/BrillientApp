package com.example.brillentapp.data.remote

import com.example.brillentapp.data.local.entity.ProductEntity
import com.example.brillentapp.domain.model.Product

data class ProductDto(val id: Int, val title: String, val price: Double, val description: String, val category: String, val image: String)

fun ProductDto.toDomain() = Product(id, title, price, description, category, image)

fun ProductDto.toEntity(): ProductEntity {
    return ProductEntity(id, title, price, description, category, image)
}



fun ProductEntity.toDomain(): Product {
    return Product(
        id = this.id,
        title = this.title,
        price = this.price,
        description = this.description,
        category = this.category,
        image = this.image
    )
}