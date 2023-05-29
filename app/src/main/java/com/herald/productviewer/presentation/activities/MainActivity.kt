package com.herald.productviewer.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.herald.productviewer.R
import com.herald.productviewer.databinding.ActivityMainBinding
import com.herald.productviewer.presentation.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding :ActivityMainBinding by lazy { DataBindingUtil.setContentView(this, R.layout.activity_main) }

    private val viewModel: ProductsViewModel by  viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.txtTest.text = "howdy"
        viewModel.state.observe(this){
            Log.i("TAG", "onCreate: $it")
            binding.txtTest.text = it.products ?.size.toString()
        }
    }
}