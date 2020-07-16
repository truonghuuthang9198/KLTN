package com.example.kltn.screen.cart.model

data class CheckBillModel(
    val maSach: String,
    val tenSach: String,
    var soLuong: Int,
    val giaTien: Double,
    val image: String
)