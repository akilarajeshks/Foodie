package com.zestworks.foodie.data.memory

import com.zestworks.foodie.data.model.Product
import com.zestworks.foodie.data.model.SalePrice
import com.zestworks.foodie.data.network.ItemListService
import com.zestworks.foodie.ui.list.goodResponse
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class InMemoryItemRepositoryTest {
    private val itemListService = mockk<ItemListService>()
    private val inMemoryItemRepository = InMemoryItemRepository(itemListService)

    @Test
    fun `Check if product detail is filtered correctly`() = runBlockingTest {
        coEvery { itemListService.getItemListResponse() } returns Response.success(goodResponse)
        inMemoryItemRepository.getListOfItems()

        inMemoryItemRepository.getProductDetail("1", "36803") shouldBe Product(
            id = "1",
            description = "",
            salePrice = SalePrice(
                amount = "0.81",
                currency = "EUR"
            ),
            categoryId = "36803",
            name = "Cola",
            url = "/Cola.jpg"
        )

    }
}