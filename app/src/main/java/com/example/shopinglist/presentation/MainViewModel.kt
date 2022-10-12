package com.example.shopinglist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shopinglist.data.ShopListImpl
import com.example.shopinglist.data.ShopListImpl.editShopList
import com.example.shopinglist.data.ShopListImpl.getShopList
import com.example.shopinglist.domain.DeleteItemUseCase
import com.example.shopinglist.domain.EditShopListUseCase
import com.example.shopinglist.domain.GetShopListUseCase
import com.example.shopinglist.domain.ShopItem

class MainViewModel : ViewModel() {

    private val repository = ShopListImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteItemUseCase = DeleteItemUseCase(repository)
    private val editShopListUseCase = EditShopListUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopList(shopItem:ShopItem){
        deleteItemUseCase.deleteItem(shopItem)
    }

    fun changeEnabledList(shopItem: ShopItem){
        val itemCopy = shopItem.copy(enabled = !shopItem.enabled)
        editShopListUseCase.editShopList(itemCopy)

    }



}