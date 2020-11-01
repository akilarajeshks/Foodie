package com.zestworks.foodie.ui.list

import com.zestworks.foodie.data.memory.ItemListRepository
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
class ListViewModelTest {
    private val itemListRepository = mockk<ItemListRepository>()
    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private var listViewModel =
        ListViewModel(itemListRepository, testCoroutineDispatcher)

    @Test
    fun `Item list loads successfully`() = runBlockingTest {
        coEvery { itemListRepository.getListOfItems() } returns successNetworkResponse

        val actualViewStates = mutableListOf<LCE<ItemListViewState>>()
        val job = launch {
            listViewModel.itemListResponse.collect {
                actualViewStates.add(it)
            }
        }
        listViewModel.onUIStarted()

        actualViewStates shouldBe listOf(
            LCE.Loading,
            LCE.Content(
                ItemListViewState(
                    listOf(
                        CategoryRow("Food"),
                        ProductRow(
                            ProductInfo(
                                "Bread",
                                "http://mobcategories.s3-website-eu-west-1.amazonaws.com/Bread.jpg"
                            )
                        ),
                        ProductRow(
                            ProductInfo(
                                "Sandwich",
                                "http://mobcategories.s3-website-eu-west-1.amazonaws.com/Sandwich.jpg"
                            )
                        ),
                        ProductRow(
                            ProductInfo(
                                "Milk",
                                "http://mobcategories.s3-website-eu-west-1.amazonaws.com/Milk.jpg"
                            )
                        ),
                        ProductRow(
                            ProductInfo(
                                "Cake",
                                "http://mobcategories.s3-website-eu-west-1.amazonaws.com/Cake.jpg"
                            )
                        ),
                        CategoryRow("Drink"),
                        ProductRow(
                            ProductInfo(
                                "Cola",
                                "http://mobcategories.s3-website-eu-west-1.amazonaws.com/Cola.jpg"
                            )
                        ),
                        ProductRow(
                            ProductInfo(
                                "Fanta",
                                "http://mobcategories.s3-website-eu-west-1.amazonaws.com/Fanta.jpg"
                            )
                        ),
                        ProductRow(
                            ProductInfo(
                                "Juice",
                                "http://mobcategories.s3-website-eu-west-1.amazonaws.com/Juice.jpg"
                            )
                        ),
                        ProductRow(
                            ProductInfo(
                                "Beer",
                                "http://mobcategories.s3-website-eu-west-1.amazonaws.com/Beer.jpg"
                            )
                        ),
                    )
                )
            )
        )
        job.cancel()
    }

    @Test
    fun `Item list errors out while loading`() = runBlockingTest {
        coEvery { itemListRepository.getListOfItems() } returns errorNetworkResponse
        val viewState = mutableListOf<LCE<ItemListViewState>>()

        val job = launch(testCoroutineDispatcher) {
            listViewModel.itemListResponse.collect {
                viewState.add(it)
            }
        }

        listViewModel.onUIStarted()

        viewState shouldBe listOf(
            LCE.Loading,
            LCE.Error(errorNetworkResponse.reason)
        )
        job.cancel()
    }
}