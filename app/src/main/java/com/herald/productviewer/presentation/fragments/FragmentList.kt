package com.herald.productviewer.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.herald.productviewer.R
import com.herald.productviewer.databinding.FragmentListBinding
import com.herald.productviewer.presentation.ProductsAdapter
import com.herald.productviewer.presentation.ProductsViewModel

class FragmentList : Fragment(R.layout.fragment_list) {

    private val binding: FragmentListBinding by lazy { FragmentListBinding.inflate(layoutInflater) }

    private val viewModel: ProductsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        viewModel.state.observe(this.viewLifecycleOwner) {
            it.products?.let { productsList ->
                binding.recyclerProducts.adapter= ProductsAdapter(productsList , findNavController())
            }
            it.error?.run {
                showErrorDialog(it.error){
                    viewModel.getProducts()
                }
            }
        }
        return binding.root
    }

    private fun showErrorDialog( message: String, retryFun: () -> Unit) {
        AlertDialog.Builder(requireContext())
            .setMessage("Error message: $message")
            .setTitle("An error has occurred")
            .setPositiveButton("Retry!") { _, _ ->
                retryFun.invoke()
            }
            .setNegativeButton("Dismiss!", null)
            .show().run {
                getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.red
                    )
                )
                getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.red
                    )
                )
            }
    }
}