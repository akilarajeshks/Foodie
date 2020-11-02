package com.zestworks.foodie.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zestworks.foodie.data.memory.ItemListRepository
import com.zestworks.foodie.data.model.Product
import com.zestworks.foodie.ui.common.LCE
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val itemRepository: ItemListRepository, private val coroutineDispatcher: CoroutineDispatcher) : ViewModel() {

    private val _viewState = MutableStateFlow<LCE<Product>>(LCE.Loading)
    val viewState: Flow<LCE<Product>> = _viewState

    fun loadProductDetail(productID: String, categoryID: String) {
        viewModelScope.launch(coroutineDispatcher) {
            val productDetail = itemRepository.getProductDetail(productID, categoryID)
            _viewState.emit(LCE.Content(productDetail))
        }
    }
}