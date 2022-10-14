package com.example.shopinglist.presentation

import androidx.lifecycle.ViewModel
import com.example.shopinglist.domain.DeleteItemUseCase
import com.example.shopinglist.domain.GetShopListUseCase

class MainViewModel:ViewModel() {

    private val getShopListUseCase = GetShopListUseCase()
    private val deleteItemUseCase = DeleteItemUseCase()


}