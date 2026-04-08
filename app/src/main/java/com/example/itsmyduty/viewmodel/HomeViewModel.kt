package com.example.itsmyduty.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.itsmyduty.data.model.Service
import com.example.itsmyduty.data.repository.FirebaseRepository

class HomeViewModel : ViewModel() {

    private val repo = FirebaseRepository()

    private val _services = mutableStateOf<List<Service>>(emptyList())
    val services: State<List<Service>> = _services

    init {
        loadServices()
    }

    fun loadServices() {
        repo.getServices {
            _services.value = it
        }
    }
}
