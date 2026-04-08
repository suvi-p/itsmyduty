package com.example.itsmyduty.data.model

data class Shop(
    val shopId:String="",
    val shopName:String="",
    val ownerName:String="",
    val address:String="",
    val rating:Double=0.0,
    val verified:Boolean=false
)