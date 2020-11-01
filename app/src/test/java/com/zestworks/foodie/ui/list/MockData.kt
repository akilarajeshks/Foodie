package com.zestworks.foodie.ui.list

import com.google.gson.Gson
import com.zestworks.foodie.data.DataResponse
import com.zestworks.foodie.data.model.ItemResponse

private val gson = Gson()
private const val mockResponse = "[{\n" +
        "\t\t\"id\": \"36802\",\n" +
        "\t\t\"name\": \"Food\",\n" +
        "\t\t\"description\": \"\",\n" +
        "\t\t\"products\": [{\n" +
        "\t\t\t\"id\": \"1\",\n" +
        "\t\t\t\"categoryId\": \"36802\",\n" +
        "\t\t\t\"name\": \"Bread\",\n" +
        "\t\t\t\"url\": \"/Bread.jpg\",\n" +
        "\t\t\t\"description\": \"\",\n" +
        "\t\t\t\"salePrice\": {\n" +
        "\t\t\t\t\"amount\": \"0.81\",\n" +
        "\t\t\t\t\"currency\": \"EUR\"\n" +
        "\t\t\t}\n" +
        "\t\t},{\n" +
        "\t\t\t\"id\": \"2\",\n" +
        "\t\t\t\"categoryId\": \"36802\",\n" +
        "\t\t\t\"name\": \"Sandwich\",\n" +
        "\t\t\t\"url\": \"/Sandwich.jpg\",\n" +
        "\t\t\t\"description\": \"\",\n" +
        "\t\t\t\"salePrice\": {\n" +
        "\t\t\t\t\"amount\": \"2.01\",\n" +
        "\t\t\t\t\"currency\": \"EUR\"\n" +
        "\t\t\t}\n" +
        "\t\t},{\n" +
        "\t\t\t\"id\": \"3\",\n" +
        "\t\t\t\"categoryId\": \"36802\",\n" +
        "\t\t\t\"name\": \"Milk\",\n" +
        "\t\t\t\"url\": \"/Milk.jpg\",\n" +
        "\t\t\t\"description\": \"\",\n" +
        "\t\t\t\"salePrice\": {\n" +
        "\t\t\t\t\"amount\": \"2.00\",\n" +
        "\t\t\t\t\"currency\": \"EUR\"\n" +
        "\t\t\t}\n" +
        "\t\t},{\n" +
        "\t\t\t\"id\": \"4\",\n" +
        "\t\t\t\"categoryId\": \"36802\",\n" +
        "\t\t\t\"name\": \"Cake\",\n" +
        "\t\t\t\"url\": \"/Cake.jpg\",\n" +
        "\t\t\t\"description\": \"\",\n" +
        "\t\t\t\"salePrice\": {\n" +
        "\t\t\t\t\"amount\": \"0.01\",\n" +
        "\t\t\t\t\"currency\": \"EUR\"\n" +
        "\t\t\t}\n" +
        "\t\t}]\n" +
        "},{\n" +
        "\t\t\"id\": \"36803\",\n" +
        "\t\t\"name\": \"Drink\",\n" +
        "\t\t\"description\": \"\",\n" +
        "\t\t\"products\": [{\n" +
        "\t\t\t\"id\": \"1\",\n" +
        "\t\t\t\"categoryId\": \"36803\",\n" +
        "\t\t\t\"name\": \"Cola\",\n" +
        "\t\t\t\"url\": \"/Cola.jpg\",\n" +
        "\t\t\t\"description\": \"\",\n" +
        "\t\t\t\"salePrice\": {\n" +
        "\t\t\t\t\"amount\": \"0.81\",\n" +
        "\t\t\t\t\"currency\": \"EUR\"\n" +
        "\t\t\t}\n" +
        "\t\t},{\n" +
        "\t\t\t\"id\": \"2\",\n" +
        "\t\t\t\"categoryId\": \"36803\",\n" +
        "\t\t\t\"name\": \"Fanta\",\n" +
        "\t\t\t\"url\": \"/Fanta.jpg\",\n" +
        "\t\t\t\"description\": \"\",\n" +
        "\t\t\t\"salePrice\": {\n" +
        "\t\t\t\t\"amount\": \"2.01\",\n" +
        "\t\t\t\t\"currency\": \"EUR\"\n" +
        "\t\t\t}\n" +
        "\t\t},{\n" +
        "\t\t\t\"id\": \"3\",\n" +
        "\t\t\t\"categoryId\": \"36803\",\n" +
        "\t\t\t\"name\": \"Juice\",\n" +
        "\t\t\t\"url\": \"/Juice.jpg\",\n" +
        "\t\t\t\"description\": \"\",\n" +
        "\t\t\t\"salePrice\": {\n" +
        "\t\t\t\t\"amount\": \"2.00\",\n" +
        "\t\t\t\t\"currency\": \"EUR\"\n" +
        "\t\t\t}\n" +
        "\t\t},{\n" +
        "\t\t\t\"id\": \"4\",\n" +
        "\t\t\t\"categoryId\": \"36803\",\n" +
        "\t\t\t\"name\": \"Beer\",\n" +
        "\t\t\t\"url\": \"/Beer.jpg\",\n" +
        "\t\t\t\"description\": \"\",\n" +
        "\t\t\t\"salePrice\": {\n" +
        "\t\t\t\t\"amount\": \"0.01\",\n" +
        "\t\t\t\t\"currency\": \"EUR\"\n" +
        "\t\t\t}\n" +
        "\t\t}]\n" +
        "}]"

private val goodResponse by lazy { gson.fromJson(mockResponse, Array<ItemResponse>::class.java).toList() }

val successNetworkResponse by lazy { DataResponse.Success(goodResponse) }

val errorNetworkResponse = DataResponse.Error("network failed")
