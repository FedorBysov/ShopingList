package com.example.shopinglist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopinglist.data.ShopListImpl
import com.example.shopinglist.domain.DeleteItemUseCase
import com.example.shopinglist.domain.EditShopListUseCase
import com.example.shopinglist.domain.GetShopListUseCase
import com.example.shopinglist.domain.ShopItem
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getShopListUseCase : GetShopListUseCase,
    private val deleteItemUseCase : DeleteItemUseCase,
    private val editShopListUseCase : EditShopListUseCase
) : ViewModel() {


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