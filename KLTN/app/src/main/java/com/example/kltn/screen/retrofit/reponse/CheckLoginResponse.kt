package com.example.kltn.screen.retrofit.reponse
import com.google.gson.annotations.SerializedName


data class CheckLoginResponse(
    @SerializedName("khachHang")
    val khachHang: KhachHang,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
) {
    data class KhachHang(
        @SerializedName("diaChi")
        val diaChi: String,
        @SerializedName("email")
        val email: String,
        @SerializedName("hoaDons")
        val hoaDons: Any,
        @SerializedName("maKhachHang")
        val maKhachHang: String,
        @SerializedName("matKhau")
        val matKhau: String,
        @SerializedName("soDiaChis")
        val soDiaChis: Any,
        @SerializedName("soDienThoai")
        val soDienThoai: String,
        @SerializedName("tenKhachHang")
        val tenKhachHang: String,
        @SerializedName("thongBaos")
        val thongBaos: Any,
        @SerializedName("yeuThiches")
        val yeuThiches: Any
    )
}