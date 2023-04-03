package com.example.shopinglist.domain

import javax.inject.Inject

class EditShopListUseCase @Inject constructor(private val shopListRepository: ShopListRepository) {

    suspend fun editShopList(shopItem: ShopItem){
        shopListRepository.editShopList(shopItem)
    }

}