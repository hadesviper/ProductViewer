package com.herald.productviewer.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.herald.productviewer.R
import com.herald.productviewer.databinding.FragmentDetailsBinding

class FragmentDetails:Fragment(R.layout.fragment_details) {

    private val binding: FragmentDetailsBinding by lazy { FragmentDetailsBinding.inflate(layoutInflater) }
    private val args :FragmentDetailsArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        Glide.with(requireContext())
            .load(args.imageUrl)
            .error(ContextCompat.getDrawable(requireContext(), R.drawable.not_found))
            .into(binding.productImage)

        binding.productName.text = args.productName

        return binding.root
    }
}