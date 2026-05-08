package com.example.brillentapp.presentation.product_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.brillentapp.R
import com.example.brillentapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint

import androidx.fragment.app.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.brillentapp.presentation.product_detail.ProductDetailFragment


@AndroidEntryPoint
class ProductListFragment : Fragment(R.layout.fragment_product_list) {

    private val viewModel: ProductListViewModel by viewModels()
    private lateinit var adapter: ProductAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Adapter Setup
        adapter = ProductAdapter { product ->
            // Detail Screen par javani logic
//            val fragment = ProductDetailFragment().apply {
//                arguments = Bundle().apply {
//                    putInt("productId", product.id)
//                }
//            }
//
//            parentFragmentManager.beginTransaction()
//                .replace(R.id.fragment_container_view, fragment) // R.id.fragment_container_view tamara Activity na layout ma hovu joie
//                .addToBackStack(null)
//                .commit()

            val bundle = Bundle().apply {
                putInt("productId", product.id)
            }

            // NavController no upyog karo
            findNavController().navigate(
                R.id.productDetailFragment, // Aa ID tamara 'nav_graph.xml' ma hovo joie
                bundle
            )
        }

        view.findViewById<RecyclerView>(R.id.rvProducts).adapter = adapter

        // Search Logic
        view.findViewById<EditText>(R.id.etSearch).addTextChangedListener { text ->
            viewModel.getProducts(text.toString())
        }

        // Observer
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect { resource ->
                when (resource) {
                    is Resource.Loading -> { /* Show Loader */ }
                    is Resource.Success -> { adapter.submitList(resource.data) }
                    is Resource.Error -> { /* Show Toast */ }
                }
            }
        }
    }
}