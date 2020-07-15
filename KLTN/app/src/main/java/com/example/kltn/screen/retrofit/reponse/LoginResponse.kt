package com.example.kltn.screen.retrofit.reponse
import com.google.gson.annotations.SerializedName


data class LoginResponse(
    @SerializedName("diaChi")
    val diaChi: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("maKhachHang")
    val maKhachHang: String,
    @SerializedName("soDienThoai")
    val soDienThoai: String,
    @SerializedName("tenKhachHang")
    val tenKhachHang: String,
    @SerializedName("token")
    val token: String
)