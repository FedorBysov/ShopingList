package com.example.shopinglist.domain


data class ShopItem(

    val name: String,
    val count: Int,
    val enabled: Boolean,
    var id: Int = Unknown_ID
){
    companion object{
        const val Unknown_ID = 0
    }
}
