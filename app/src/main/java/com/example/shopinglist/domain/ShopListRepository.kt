package com.example.shopinglist.domain

interface ShopListRepository {

    fun addShopItem(shopItem: ShopItem)

    fun deleteItem(shopItem: ShopItem)

    fun getShopItem(ShopItemId: Int): ShopItem

    fun getShopList(): List<ShopItem>

}