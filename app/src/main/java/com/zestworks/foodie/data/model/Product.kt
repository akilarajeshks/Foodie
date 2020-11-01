package com.zestworks.foodie.data.model


import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("categoryId")
    val categoryId: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("salePrice")
    val salePrice: SalePrice,
    @SerializedName("url")
    val url: String
)