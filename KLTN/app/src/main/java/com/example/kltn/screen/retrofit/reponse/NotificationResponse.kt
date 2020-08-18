package com.example.kltn.screen.retrofit.reponse
import com.google.gson.annotations.SerializedName



data class NotificationResponse(
    @SerializedName("khachHangResource")
    val khachHangResource: String?,
    @SerializedName("maKhachHang")
    val maKhachHang: String,
    @SerializedName("maThongBao")
    val maThongBao: String,
    @SerializedName("ngayThongBao")
    val ngayThongBao: String,
    @SerializedName("noiDung")
    val noiDung: String,
    @SerializedName("tenThongBao")
    val tenThongBao: String
)