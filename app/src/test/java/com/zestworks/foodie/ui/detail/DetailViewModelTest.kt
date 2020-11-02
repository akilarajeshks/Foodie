package com.zestworks.foodie.ui.detail

import com.zestworks.foodie.data.memory.ItemListRepository
import com.zestworks.foodie.data.model.Product
import com.zestworks.foodie.data.model.SalePrice
import com.zestworks.foodie.ui.common.LCE
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailViewModelTest {

    private val itemListRepository = mockk<ItemListRepository>()
    private val detailViewModel =
        DetailViewModel(itemRepository = itemListRepository, TestCoroutineDispatcher())

    @Test
    fun `Product list successfully fetched`() = runBlockingTest {
        coEvery { itemListRepository.getProductDetail("1", "36803") } returns Product(
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

        val actualViewStates = mutableListOf<LCE<Product>>()

        val job = launch {
            detailViewModel.viewState.collect {
                actualViewStates.add(it)
            }
        }

        detailViewModel.loadProductDetail(
            productID = "1",
            categoryID = "36803"
        )

        actualViewStates shouldBe listOf(
            LCE.Loading,
            LCE.Content(
                Product(
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
            )
        )
        job.cancel()

    }
}