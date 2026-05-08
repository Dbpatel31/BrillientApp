package com.example.brillentapp.presentation.product_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.brillentapp.R
import com.example.brillentapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProductDetailFragment : Fragment(R.layout.fragment_product_detail) {

    private val viewModel: ProductDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val productId = arguments?.getInt("productId") ?: return

        viewModel.getProductDetail(productId)


        lifecycleScope.launchWhenStarted {
            viewModel.state.collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        resource.data?.let { product ->
                            view.findViewById<TextView>(R.id.tvTitleDetail).text = product.title
                            view.findViewById<TextView>(R.id.tvPriceDetail).text = "$${product.price}"
                            view.findViewById<TextView>(R.id.tvDescriptionDetail).text = product.description
                            Glide.with(requireContext()).load(product.image).into(view.findViewById(R.id.ivProductDetail))
                        }
                    }
                    is Resource.Loading -> { /* Show Loader */ }
                    is Resource.Error -> { /* Show Toast */ }
                }
            }
        }
    }
}