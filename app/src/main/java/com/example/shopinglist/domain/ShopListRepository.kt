package com.example.shopinglist.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {

    //MainActivity

    suspend fun addShopItem(shopItem: ShopItem)

    suspend fun deleteItem(shopItem: ShopItem)

    suspend fun editShopList(shopItem: ShopItem)

    suspend fun getShopItem(shopItemId: Int): ShopItem

     fun getShopList(): LiveData<List<ShopItem>>

}