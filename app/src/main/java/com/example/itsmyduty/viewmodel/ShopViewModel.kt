package com.example.itsmyduty.viewmodel

import androidx.lifecycle.ViewModel
import com.example.itsmyduty.data.model.Shop
import com.example.itsmyduty.data.repository.ShopRepository
import java.util.UUID

class ShopViewModel : ViewModel() {

    private val repository = ShopRepository()

    fun registerShop(
        shopName: String,
        ownerName: String,
        address: String
    ) {

        val shopId = UUID.randomUUID().toString()

        val shop = Shop(
            shopId = shopId,
            shopName = shopName,
            ownerName = ownerName,
            address = address,
            verified = false
        )

        repository.registerShop(shop)

    }
}