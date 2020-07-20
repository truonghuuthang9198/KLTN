package com.example.kltn.screen.retrofit.reponse
import com.google.gson.annotations.SerializedName

data class ManangerAddressResponse(
    @SerializedName("diaChi")
    val diaChi: String,
    @SerializedName("ho")
    val ho: String,
    @SerializedName("loaiDiaChi")
    val loaiDiaChi: Int,
    @SerializedName("maKhachHang")
    val maKhachHang: String,
    @SerializedName("maSo")
    val maSo: String,
    @SerializedName("phuong")
    val phuong: String,
    @SerializedName("quan")
    val quan: String,
    @SerializedName("soDienThoai")
    val soDienThoai: String,
    @SerializedName("ten")
    val ten: String,
    @SerializedName("thanhPho")
    val thanhPho: String
)
