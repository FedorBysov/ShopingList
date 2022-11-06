package com.example.shopinglist.domain

class AddShopItemUseCase(private val shopListRepository: ShopListRepository) {

    suspend fun addShopItemUseCase(shopItem: ShopItem){
        return shopListRepository.addShopItem(shopItem)
    }
}