package com.herald.productviewer.presentation.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.herald.productviewer.R
import com.herald.productviewer.databinding.ActivityMainBinding
import com.herald.productviewer.presentation.ProductsAdapter
import com.herald.productviewer.presentation.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding :ActivityMainBinding by lazy { DataBindingUtil.setContentView(this, R.layout.activity_main) }

    private val viewModel: ProductsViewModel by  viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.state.observe(this){
            it.products?.let {productsList->
                binding.recyclerProducts.adapter= ProductsAdapter(productsList)
            }
        }
    }
}