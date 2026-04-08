package com.example.itsmyduty.data.repository

import com.example.itsmyduty.data.model.Booking
import com.google.firebase.firestore.FirebaseFirestore

class BookingRepository {

    private val db = FirebaseFirestore.getInstance()

    fun createBooking(booking: Booking) {

        db.collection("bookings")
            .add(booking)

    }

    fun updateBookingStatus(
        bookingId: String,
        status: String
    ) {

        db.collection("bookings")
            .document(bookingId)
            .update("status", status)

    }

    fun listenBookingStatus(

        bookingId: String,
        onUpdate: (String) -> Unit
    ) {

        db.collection("bookings")
            .document(bookingId)
            .addSnapshotListener { snapshot, error ->

                if (snapshot != null && snapshot.exists()) {

                    val status = snapshot.getString("status")

                    if (status != null) {
                        onUpdate(status)
                    }

                }

            }

    }

}