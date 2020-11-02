package com.zestworks.foodie.data.memory

import com.zestworks.foodie.data.DataResponse
import com.zestworks.foodie.data.model.ItemResponse
import com.zestworks.foodie.data.model.Product
import com.zestworks.foodie.data.network.ItemListService

class InMemoryItemRepository(private val itemListService: ItemListService) : ItemListRepository {

    private lateinit var listOfItems: List<ItemResponse>

    override suspend fun getListOfItems(): DataResponse<List<ItemResponse>> {
        val itemListResponse = itemListService.getItemListResponse()
        return if (itemListResponse.isSuccessful) {
            val itemListResponseBody = itemListResponse.body()
            if (itemListResponseBody != null) {
                listOfItems = itemListResponseBody
                DataResponse.Success(itemListResponseBody)
            } else {
                DataResponse.Error("Response body is null")
            }
        } else {
            DataResponse.Error("Response unsuccessful")
        }
    }

    override suspend fun getProductDetail(productID: String, categoryID: String): Product {
        return listOfItems.find { it.id == categoryID }?.products?.find { it.id == productID }!!
    }
}