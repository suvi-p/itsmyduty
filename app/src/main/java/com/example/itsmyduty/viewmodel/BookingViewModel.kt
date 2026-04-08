package com.example.itsmyduty.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.itsmyduty.data.repository.BookingRepository

class BookingViewModel : ViewModel() {

    private val repo = BookingRepository()

    val status = MutableLiveData<String>()

    fun trackBooking(bookingId: String) {

        repo.listenBookingStatus(bookingId) { newStatus ->

            status.postValue(newStatus)

        }

    }

}