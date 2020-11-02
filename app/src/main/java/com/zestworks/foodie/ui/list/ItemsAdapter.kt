package com.zestworks.foodie.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.zestworks.foodie.R

class ItemsAdapter(
    private var listOfItems: List<ItemRow>,
    val clickCallback: (String, String) -> Unit
) :
    RecyclerView.Adapter<ItemsViewHolder>() {
    private val CATEGORY_ITEM_TYPE = 1
    private val PRODUCT_ITEM_TYPE = 2
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        return when (viewType) {
            CATEGORY_ITEM_TYPE ->
                CategoryItemViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_category_holder, parent, false)
                )
            PRODUCT_ITEM_TYPE ->
                ProductItemViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_product_holder, parent, false)
                )
            else ->
                CategoryItemViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_category_holder, parent, false)
                )
        }

    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        val itemViewType = holder.itemViewType
        val currentItem = listOfItems[position]
        when (itemViewType) {
            CATEGORY_ITEM_TYPE -> {
                if (holder is CategoryItemViewHolder && currentItem is CategoryRow) {
                    holder.itemCategoryName.text = currentItem.categoryTitle
                }
            }
            PRODUCT_ITEM_TYPE -> {
                if (holder is ProductItemViewHolder && currentItem is ProductRow) {
                    holder.itemView.setOnClickListener {
                        clickCallback(
                            currentItem.productInfo.id,
                            currentItem.productInfo.categoryId
                        )
                    }
                    holder.itemName.text = currentItem.productInfo.name
                    val url = currentItem.productInfo.imageUrl
                    Glide
                        .with(holder.itemImage.context)
                        .load(url)
                        .centerInside()
                        .override(150, 150)
                        .transform(CircleCrop())
                        .error(R.drawable.ic_baseline_broken_image_24)
                        .into(holder.itemImage)

                }
            }
        }
    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }

    override fun getItemViewType(position: Int): Int {
        if (listOfItems[position] is CategoryRow) {
            return CATEGORY_ITEM_TYPE
        } else if (listOfItems[position] is ProductRow) {
            return PRODUCT_ITEM_TYPE
        }
        return super.getItemViewType(position)
    }

    fun updateItems(items: List<ItemRow>) {
        this.listOfItems = items
        notifyDataSetChanged()
    }
}


abstract class ItemsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
class CategoryItemViewHolder(itemView: View) : ItemsViewHolder(itemView) {
    val itemCategoryName: TextView = itemView.findViewById(R.id.item_category_name)
}

class ProductItemViewHolder(itemView: View) : ItemsViewHolder(itemView) {
    val itemImage: ImageView = itemView.findViewById(R.id.item_image)
    val itemName: TextView = itemView.findViewById(R.id.item_name)
}
