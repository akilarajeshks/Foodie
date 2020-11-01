package com.zestworks.foodie.data.memory

import com.zestworks.foodie.data.DataResponse
import com.zestworks.foodie.data.model.ItemResponse

interface ItemListRepository {
    suspend fun getListOfItems() : DataResponse<List<ItemResponse>>
}