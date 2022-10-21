package com.example.shopinglist.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.shopinglist.R
import com.example.shopinglist.domain.ShopItem
import com.example.shopinglist.presentation.ShopItemDiffCallBack

class AdapterMainActivity : ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemDiffCallBack()) {

    var count = 0
//    var listItem: List<ShopItem> = listOf()
//        set(value) {
//            val callback = ShopListDiffUtilCallBack(listItem, value)
//            val diffResult =DiffUtil.calculateDiff(callback)
//            diffResult.dispatchUpdatesTo(this)
//            field = value
//        }

    var onShopItemCoinLongCliClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemCoinCliClickListener: ((ShopItem) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        Log.d("CreateViewHolder", "CreateViewHolder ${++count}")
        val layout = when (viewType) {
            ENABLED_VIEW -> R.layout.item_note_info
            DISABLED_VIEW -> R.layout.item_hind_note_info
            else -> throw RuntimeException("Error in onCreateViewHolder")
        }
        val view =
            LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
        with(holder) {
            text.text = shopItem.name
            count.text = shopItem.count.toString()

            itemView.setOnLongClickListener {
                onShopItemCoinLongCliClickListener?.invoke(shopItem)
                true
            }
            itemView.setOnClickListener {
                onShopItemCoinCliClickListener?.invoke(shopItem)
            }
            //            itemView.setOnClickListener {
//                onCoinClickListener?.OnCoinClick(coin)
//            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val list = getItem(position)
        return if (list.enabled) {
            ENABLED_VIEW
        } else {
            DISABLED_VIEW
        }
//        return super.getItemViewType(position)
    }

//    override fun onViewRecycled(holder: MainActivityHolder) {
//        super.onViewRecycled(holder)
//        holder.text.text = ""
//        holder.count.text = ""
//    }


//    interface OnShopItemLongCoinCliClickListener{
//        fun onShopItemCoinClick(shopItem: ShopItem)
//    }

    companion object {
        const val ENABLED_VIEW = 100
        const val DISABLED_VIEW = 101

        const val POOL_VIEW_ACTIV = 15
        const val POOL_VIEW_DISABLE = 15
    }

}