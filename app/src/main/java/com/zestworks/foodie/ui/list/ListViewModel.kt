package com.zestworks.foodie.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zestworks.foodie.common.LCE
import com.zestworks.foodie.data.DataResponse
import com.zestworks.foodie.data.memory.ItemListRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ListViewModel(
    private val itemListRepository: ItemListRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _itemListResponse = MutableStateFlow<LCE<ItemListViewState>>(LCE.Loading)
    val itemListResponse: Flow<LCE<ItemListViewState>> = _itemListResponse

    fun onUIStarted() {
        viewModelScope.launch(dispatcher) {
            fetchItems()
        }
    }

    private suspend fun fetchItems() {
        _itemListResponse.value =
            when (val itemListResponse = itemListRepository.getListOfItems()) {
                is DataResponse.Success -> {
                    val listOfItems = itemListResponse.data
                    val itemRows = mutableListOf<ItemRow>()
                    listOfItems.forEach { item ->
                        itemRows.add(CategoryRow(item.name))
                        itemRows.addAll(item.products.map {
                            ProductRow(
                                ProductInfo(
                                    name = it.name,
                                    imageUrl = "http://mobcategories.s3-website-eu-west-1.amazonaws.com${it.url}",
                                    id = it.id,
                                    categoryId = it.categoryId
                                )
                            )
                        })
                    }
                    LCE.Content(ItemListViewState(itemRows))
                }
                is DataResponse.Error -> {
                    LCE.Error(itemListResponse.reason)
                }
            }

    }
}

