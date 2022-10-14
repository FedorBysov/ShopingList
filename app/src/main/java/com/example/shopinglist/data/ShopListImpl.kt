package com.example.shopinglist.data

import com.example.shopinglist.domain.ShopItem
import com.example.shopinglist.domain.ShopListRepository

object ShopListImpl : ShopListRepository {

    private val shopList = mutableListOf<ShopItem>()

    private var autoDefinitionId = 0

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.Unknown_ID) {
            shopItem.id = autoDefinitionId++
        }
        shopList.add(shopItem)
    }

    override fun deleteItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
    }

    override fun editShopList(shopItem: ShopItem) {
        val newElement = getShopItem(shopItem.id)
        shopList.remove(newElement)
        addShopItem(shopItem)
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find { it.id == shopItemId } ?: throw RuntimeException("NO MATCH BY ID")
    }

    override fun getShopList(): List<ShopItem> {
        return shopList.toList()
    }
}