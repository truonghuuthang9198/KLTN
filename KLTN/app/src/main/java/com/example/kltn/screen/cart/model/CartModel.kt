package com.example.kltn.screen.cart.model

data class CartModel(
    val id:Int,
    val tenSach: String,
    var soLuong: Int,
    val giaTien: Double,
    val image: Int
)