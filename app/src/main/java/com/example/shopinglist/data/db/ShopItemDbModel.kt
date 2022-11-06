package com.example.shopinglist.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shopinglist.domain.ShopItem

@Entity(tableName = "shop_items")
data class ShopItemDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = ShopItem.Unknown_ID,
    val name: String,
    val count: Int,
    val enabled: Boolean,
)