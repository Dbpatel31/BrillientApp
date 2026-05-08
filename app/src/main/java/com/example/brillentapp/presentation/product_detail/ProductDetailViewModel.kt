package com.example.brillentapp.presentation.product_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brillentapp.domain.model.Product
import com.example.brillentapp.domain.usecase.GetProductDetailUseCase
import com.example.brillentapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val getProductDetailUseCase: GetProductDetailUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<Resource<Product>>(Resource.Loading())
    val state: StateFlow<Resource<Product>> = _state

    fun getProductDetail(id: Int) {
        getProductDetailUseCase(id).onEach { result ->
            _state.value = result
        }.launchIn(viewModelScope)
    }
}