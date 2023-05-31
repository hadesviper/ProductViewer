package com.herald.productviewer.presentation

import com.herald.productviewer.domain.models.ProductsModel

data class ProductsState (
    val isLoading:Boolean = false,
    val products:List<ProductsModel>? = emptyList(),
    val error:String? = null
)