package com.example.kltn.screen.retrofit.request

data class RegisterRequest(
    val maKhachHang: String,
    val matKhau: String,
    val tenKhachHang: String,
    val diaChi: String,
    val soDienThoai: String,
    val email: String,
    val gioiTinh: String,
    val capDoThanhVien: Int
)