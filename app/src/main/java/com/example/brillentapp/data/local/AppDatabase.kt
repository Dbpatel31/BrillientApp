package com.example.brillentapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.brillentapp.data.local.dao.ProductDao
import com.example.brillentapp.data.local.entity.ProductEntity


@Database(entities = [ProductEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val productDao: ProductDao
}