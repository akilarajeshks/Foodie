package com.zestworks.foodie.ui.list

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.zestworks.foodie.R
import com.zestworks.foodie.extensions.hide
import com.zestworks.foodie.extensions.show
import com.zestworks.foodie.ui.common.LCE
import kotlinx.android.synthetic.main.fragment_items_list.*
import kotlinx.coroutines.flow.collect
import org.koin.android.viewmodel.ext.android.viewModel

class ListFragment : Fragment(R.layout.fragment_items_list) {

    private val listViewModel: ListViewModel by viewModel()

    override fun onStart() {
        super.onStart()

        lifecycleScope.launchWhenStarted {
            listViewModel.itemListResponse.collect {
                when (it) {
                    is LCE.Content -> {
                        list_progress.hide()
                        list_recycler_view.show()
                        list_error_text.hide()

                        if (list_recycler_view.adapter == null) {
                            list_recycler_view.apply {
                                adapter = ItemsAdapter(it.viewData.itemRowData)
                                layoutManager = LinearLayoutManager(this@ListFragment.context)
                            }
                        } else {
                            (list_recycler_view.adapter as ItemsAdapter).updateItems(it.viewData.itemRowData)
                        }
                    }
                    is LCE.Error -> {
                        list_progress.hide()
                        list_recycler_view.hide()
                        list_error_text.show()

                        list_error_text.text = it.reason
                    }
                    LCE.Loading -> {
                        list_progress.show()
                        list_recycler_view.hide()
                        list_error_text.hide()

                    }
                }
            }
        }

        listViewModel.onUIStarted()
    }
}