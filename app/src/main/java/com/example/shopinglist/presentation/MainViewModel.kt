package com.example.shopinglist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopinglist.data.ShopListImpl
import com.example.shopinglist.domain.DeleteItemUseCase
import com.example.shopinglist.domain.EditShopListUseCase
import com.example.shopinglist.domain.GetShopListUseCase
import com.example.shopinglist.domain.ShopItem
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ShopListImpl(application)

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteItemUseCase = DeleteItemUseCase(repository)
    private val editShopListUseCase = EditShopListUseCase(repository)


//    private val scope = CoroutineScope(Dispatchers.IO)

    val shopList = getShopListUseCase.getShopList()


    fun deleteShopList(shopItem: ShopItem) {
        viewModelScope.launch {
            deleteItemUseCase.deleteItem(shopItem)
        }
    }

    fun changeEnabledList(shopItem: ShopItem) {
        viewModelScope.launch {
            val itemCopy = shopItem.copy(enabled = !shopItem.enabled)
            editShopListUseCase.editShopList(itemCopy)
        }

    }


}