package com.example.kltn.screen.retrofit.reponse
import com.google.gson.annotations.SerializedName


data class LoginResponse(
    @SerializedName("capDoThanhVien")
    val capDoThanhVien: Int,
    @SerializedName("diaChi")
    val diaChi: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("gioiTinh")
    val gioiTinh: String,
    @SerializedName("maKhachHang")
    val maKhachHang: String,
    @SerializedName("soDienThoai")
    val soDienThoai: String,
    @SerializedName("tenKhachHang")
    val tenKhachHang: String,
    @SerializedName("token")
    val token: String
)