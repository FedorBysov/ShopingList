package com.example.shopinglist.domain

class DeleteItemUseCase(private val shopListRepository: ShopListRepository) {

    fun deleteItem(shopItem: ShopItem) {
        shopListRepository.deleteItem(shopItem)
    }
}