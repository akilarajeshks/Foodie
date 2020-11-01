package com.zestworks.foodie.data

sealed class DataResponse<out T : Any> {
    data class Success<out T : Any>(val data: T) : DataResponse<T>()
    data class Error(val reason: String) : DataResponse<Nothing>()
}