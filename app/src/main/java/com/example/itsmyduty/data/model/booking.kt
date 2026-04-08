package com.example.itsmyduty.data.model


data class Booking(

    val bookingId: String = "",
    val userId: String = "",
    val shopId: String = "",
    val serviceId: String = "",
    val status: String = "Requested",
    val estimatedTime: String = "",
    val warranty: String = ""

)