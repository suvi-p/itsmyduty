package com.example.itsmyduty.data.repository

import com.example.itsmyduty.data.model.Shop
import com.google.firebase.firestore.FirebaseFirestore

class ShopRepository {

    private val db = FirebaseFirestore.getInstance()

    fun registerShop(shop: Shop) {

        db.collection("shops")
            .document(shop.shopId)
            .set(shop)

    }

    fun getVerifiedShops(onResult: (List<Shop>) -> Unit) {

        db.collection("shops")
            .whereEqualTo("verified", true)
            .get()
            .addOnSuccessListener {

                val shops = it.toObjects(Shop::class.java)

                onResult(shops)

            }

    }

}