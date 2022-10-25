package com.example.shopinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shopinglist.domain.ShopItem
import com.example.shopinglist.domain.ShopListRepository


object ShopListImpl : ShopListRepository {

    private val shopListLD = MutableLiveData<List<ShopItem>>()
    private val shopList = sortedSetOf<ShopItem>({t1 , t2 -> t1.id.compareTo(t2.id)})

    private var autoDefinitionId = 0

    //Блок для порверки
    init {
        for (i in 0 until 10) {
            val item = ShopItem("NAME $i", i, Math.random() > 0.5)
            addShopItem(item)
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.Unknown_ID) {
            shopItem.id = autoDefinitionId++
        }

        shopList.add(shopItem)
        updateList()
    }

    override fun deleteItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    override fun editShopList(shopItem: ShopItem) {
        val newElement = getShopItem(shopItem.id)
        shopList.remove(newElement)
        addShopItem(shopItem)
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find { it.id == shopItemId } ?: throw RuntimeException("NO MATCH BY ID")
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLD
    }

    private fun updateList(){
        shopListLD.value = shopList.toList()
    }




}