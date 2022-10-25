package com.example.shopinglist.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {

    //MainActivity

    fun addShopItem(shopItem: ShopItem)

    fun deleteItem(shopItem: ShopItem)

    fun editShopList(shopItem: ShopItem)

    fun getShopItem(shopItemId: Int): ShopItem

    fun getShopList(): LiveData<List<ShopItem>>

}