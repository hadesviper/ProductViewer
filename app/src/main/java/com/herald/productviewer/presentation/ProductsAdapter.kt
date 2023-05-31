package com.herald.productviewer.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.herald.productviewer.R
import com.herald.productviewer.databinding.ProductItemBinding
import com.herald.productviewer.domain.models.ProductsModel
import com.herald.productviewer.presentation.fragments.FragmentListDirections

class ProductsAdapter(private val itemList: List<ProductsModel>, private val findNavController: NavController) :
    RecyclerView.Adapter<ProductsAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount() = itemList.size

    inner class ItemViewHolder(private val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProductsModel,context: Context = binding.root.context) {
            Glide.with(context)
                .load(item.image)
                .error(ContextCompat.getDrawable(context, R.drawable.not_found))
                .into(binding.imgPoster)
            binding.txtName.text = item.title
            binding.txtDescription.text = item.description
            binding.txtPrice.text = item.price
            binding.viewItem.setOnClickListener {
                val action = FragmentListDirections.actionFragmentListToFragmentDetails(item.image,item.title)
                findNavController.navigate(action)
            }
        }
    }
}
