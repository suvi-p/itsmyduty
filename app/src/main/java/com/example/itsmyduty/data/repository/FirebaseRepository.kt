package com.example.itsmyduty.data.repository

import com.example.itsmyduty.data.model.Service
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseRepository {

    private val firestore = FirebaseFirestore.getInstance()

    fun getServices(onResult: (List<Service>) -> Unit) {

        firestore.collection("services")
            .get()
            .addOnSuccessListener { result ->

                val list = result.toObjects(Service::class.java)

                onResult(list)

            }

    }
}
