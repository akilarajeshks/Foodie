package com.zestworks.foodie.di

import com.zestworks.foodie.data.memory.InMemoryItemRepository
import com.zestworks.foodie.data.memory.ItemListRepository
import com.zestworks.foodie.data.network.ItemListService
import com.zestworks.foodie.ui.detail.DetailViewModel
import com.zestworks.foodie.ui.list.ListViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val applicationModule = module {
    single { provideItemListService() }
    single<ItemListRepository> { InMemoryItemRepository(get()) }
    viewModel { ListViewModel(get(), Dispatchers.IO) }
    viewModel { DetailViewModel(get(), Dispatchers.IO) }
}

private fun provideItemListService(): ItemListService {
    val baseUrl = "http://mobcategories.s3-website-eu-west-1.amazonaws.com/"
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(ItemListService::class.java)
}