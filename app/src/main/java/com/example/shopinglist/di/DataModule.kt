package com.example.shopinglist.di

import android.app.Application
import com.example.shopinglist.data.ShopListImpl
import com.example.shopinglist.data.db.AppDataBase
import com.example.shopinglist.data.db.ShopListDao
import com.example.shopinglist.data.db.ShopListMapper
import com.example.shopinglist.domain.ShopListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindShopListRepository(impl: ShopListImpl):ShopListRepository

    companion object{

        @ApplicationScope
        @Provides
        fun provideShopListDao(
            application: Application
        ):ShopListDao{
            return AppDataBase.getInstance(application).shopListDao()
        }
    }
}