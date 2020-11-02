package com.zestworks.foodie.ui.detail

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.zestworks.foodie.R
import com.zestworks.foodie.common.LCE.*
import kotlinx.android.synthetic.main.fragment_product_detail.*
import kotlinx.coroutines.flow.collect
import org.koin.android.viewmodel.ext.android.viewModel


class ProductDetailFragment : Fragment(R.layout.fragment_product_detail) {
    private val detailViewModel: DetailViewModel by viewModel()

    override fun onStart() {
        super.onStart()
        lifecycleScope.launchWhenStarted {
            detailViewModel.viewState.collect {
                when (it) {
                    is Content -> {
                        Glide.with(requireContext())
                            .load("http://mobcategories.s3-website-eu-west-1.amazonaws.com${it.viewData.url}")
                            .centerInside()
                            .override(150, 150)
                            .transform(CircleCrop())
                            .error(R.drawable.ic_baseline_broken_image_24)
                            .into(detail_product_image)
                        detail_product_name.text = it.viewData.name
                        detail_product_price.text = getString(
                            R.string.detail_currency,
                            it.viewData.salePrice.currency,
                            it.viewData.salePrice.amount
                        )
                    }
                    is Error -> {
                        Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
                    }
                    Loading -> {

                    }
                }
            }
        }
        val fromBundle = ProductDetailFragmentArgs.fromBundle(requireArguments())
        detailViewModel.loadProductDetail(fromBundle.productID, fromBundle.categoryID)
    }
}