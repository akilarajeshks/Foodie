package com.zestworks.foodie.data.network

import com.zestworks.foodie.data.model.ItemResponse
import retrofit2.Response
import retrofit2.http.GET

interface ItemListService {
    @GET(".")
    suspend fun getItemListResponse() : Response<List<ItemResponse>>
}