package com.herald.productviewer.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.herald.productviewer.common.Resources
import com.herald.productviewer.domain.usecases.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productsUseCase: GetProductsUseCase
) :ViewModel(){

    private val _state = MutableLiveData<ProductsState>()
    val state :LiveData<ProductsState> = _state

    init {
        productsUseCase().onEach {
            when (it) {
                is Resources.Loading -> {
                    _state.value = ProductsState(isLoading = true)
                }
                is Resources.Success -> {
                    _state.value = ProductsState(products =  it.data!!)
                }
                is Resources.Error -> {
                    _state.value = ProductsState(error = it.message)
                }
            }

        }.launchIn(viewModelScope)
    }
}