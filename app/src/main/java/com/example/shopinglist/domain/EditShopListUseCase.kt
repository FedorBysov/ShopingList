package com.example.shopinglist.domain

class EditShopListUseCase(private val shopListRepository: ShopListRepository) {

    suspend fun editShopList(shopItem: ShopItem){
        shopListRepository.editShopList(shopItem)
    }

}