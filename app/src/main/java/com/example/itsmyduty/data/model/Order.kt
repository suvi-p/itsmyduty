package com.example.itsmyduty.data.model

data class Order(
    val orderId:String="",
    val userId:String="",
    val shopId:String="",
    val serviceId:String="",
    val status:String="",
    val price:Int=0
)