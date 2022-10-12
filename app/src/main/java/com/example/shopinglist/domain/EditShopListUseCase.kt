package com.example.shopinglist.domain

class EditShopListUseCase(private val shopListRepository: ShopListRepository) {

    fun editShopList(shopItem: ShopItem){
        shopListRepository.editShopList(shopItem)
    }

}