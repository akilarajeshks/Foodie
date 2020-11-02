package com.zestworks.foodie.data.memory

import com.zestworks.foodie.data.DataResponse
import com.zestworks.foodie.data.model.ItemResponse
import com.zestworks.foodie.data.model.Product

interface ItemListRepository {
    suspend fun getListOfItems(): DataResponse<List<ItemResponse>>
    suspend fun getProductDetail(productID: String, categoryID: String): Product
}