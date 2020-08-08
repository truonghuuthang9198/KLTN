package com.example.kltn.screen.retrofit.request

data class UpdateAddressRequest(
    val maSo:Int,
    val maKhachHang: String,
    val diaChi: String,
    val soDienThoai: String,
    val ho: String,
    val ten: String,
    val thanhPho: String,
    val quan:String,
    val phuong:String,
    val loaiDiaChi:Int
)