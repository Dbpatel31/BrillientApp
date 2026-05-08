package com.example.brillentapp.presentation.product_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brillentapp.domain.model.Product
import com.example.brillentapp.domain.usecase.GetProductsUseCase
import com.example.brillentapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<Resource<List<Product>>>(Resource.Loading())
    val state: StateFlow<Resource<List<Product>>> = _state

    init {
        getProducts() // Initial load
    }

    fun getProducts(query: String = "") {
        getProductsUseCase(query).onEach { result ->
            _state.value = result
        }.launchIn(viewModelScope)
    }
}