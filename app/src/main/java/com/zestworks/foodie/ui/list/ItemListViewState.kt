package com.zestworks.foodie.ui.list

data class ItemListViewState(
    val itemRowData: List<ItemRow>
)

data class ProductInfo(
    val name: String,
    val imageUrl: String
)

sealed class ItemRow
data class CategoryRow(val categoryTitle: String) : ItemRow()
data class ProductRow(val productInfo: ProductInfo) : ItemRow()