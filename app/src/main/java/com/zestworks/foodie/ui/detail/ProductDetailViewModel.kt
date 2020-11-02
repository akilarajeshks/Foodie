package com.zestworks.foodie.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zestworks.foodie.common.LCE
import com.zestworks.foodie.data.memory.ItemListRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val itemRepository: ItemListRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _viewState = MutableStateFlow<LCE<ProductDetailViewState>>(LCE.Loading)
    val viewState: Flow<LCE<ProductDetailViewState>> = _viewState

    fun loadProductDetail(productID: String, categoryID: String) {
        viewModelScope.launch(coroutineDispatcher) {
            val productDetail = itemRepository.getProductDetail(productID, categoryID)
            _viewState.emit(
                LCE.Content(
                    ProductDetailViewState(
                        imageUrl = "http://mobcategories.s3-website-eu-west-1.amazonaws.com${productDetail.url}",
                        productName = productDetail.name,
                        amount = productDetail.salePrice.amount,
                        currency = productDetail.salePrice.currency
                    )
                )
            )
        }
    }
}