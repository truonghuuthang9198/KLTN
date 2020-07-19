package com.example.kltn.screen.retrofit.reponse
import com.google.gson.annotations.SerializedName

data class ManagerAddressRespone(
    @SerializedName("diaChi")
    val diaChi: String,
    @SerializedName("maKhachHang")
    val maKhachHang: String,
    @SerializedName("maSo")
    val maSo: String,
    @SerializedName("soDienThoai")
    val soDienThoai: String
)